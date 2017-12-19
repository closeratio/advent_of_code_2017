package com.aoc2017.day18_part2

import com.aoc2017.day18_part2.instructions.*
import java.util.concurrent.LinkedBlockingQueue

class Program(
        val programID: Long = 0L,
        val sendList: LinkedBlockingQueue<Long> = LinkedBlockingQueue(),
        val receiveList: LinkedBlockingQueue<Long> = LinkedBlockingQueue()) {

    val registers = mutableMapOf("p" to Register("p", programID))

    var currInstIndex = 0
    var recovered = false
    var sendCount = 0

    fun executeAllInstructions(instructions: List<Instruction>) {
        while (currInstIndex >= 0
                && currInstIndex < instructions.size
                && !recovered) {
            executeInstruction(instructions[currInstIndex])
        }
    }

    fun executeInstruction(inst: Instruction) {
        when (inst) {
            is SetInstruction -> executeSetInstruction(inst)
            is AddInstruction -> executeAddInstruction(inst)
            is MultiplyInstruction -> executeMultiplyInstruction(inst)
            is ModuloInstruction -> executeModuloInstruction(inst)
            is SendInstruction -> executeSendInstruction(inst)
            is ReceiveInstruction -> executeReceiveInstruction(inst)
            is JumpInstruction -> executeJumpInstruction(inst)
            else -> throw RuntimeException("Unhandled instruction: $inst")
        }
    }

    private fun executeJumpInstruction(inst: JumpInstruction) {
        val testVal = getValueHolderVal(inst.test)
        if (testVal > 0) {
            currInstIndex += getValueHolderVal(inst.jumpValue).toInt()
        } else {
            currInstIndex++
        }
    }

    private fun executeReceiveInstruction(inst: ReceiveInstruction) {
        getOrPutReg(inst.reg.name).value = receiveList.take()
        currInstIndex++
    }

    private fun executeSendInstruction(inst: SendInstruction) {
        sendList.put(getValueHolderVal(inst.valueHolder))
        currInstIndex++
        sendCount++
    }

    private fun executeModuloInstruction(inst: ModuloInstruction) {
        val reg = getOrPutReg(inst.reg.name)
        reg.value = reg.value % getValueHolderVal(inst.value)
        currInstIndex++
    }

    private fun executeMultiplyInstruction(inst: MultiplyInstruction) {
        val reg = getOrPutReg(inst.reg.name)
        reg.value *= getValueHolderVal(inst.value)
        currInstIndex++
    }

    private fun executeAddInstruction(inst: AddInstruction) {
        val reg = getOrPutReg(inst.reg.name)
        reg.value += getValueHolderVal(inst.value)
        currInstIndex++
    }

    private fun executeSetInstruction(inst: SetInstruction) {
        val reg = getOrPutReg(inst.reg.name)
        reg.value = getValueHolderVal(inst.value)
        currInstIndex++
    }

    private fun getOrPutReg(name: String): Register {
        return registers.getOrPut(name, { Register(name) })
    }

    private fun getValueHolderVal(vh: ValueHolder): Long {
        return when (vh) {
            is Register -> getOrPutReg(vh.name).value
            is LongValue -> vh.value
            else -> throw RuntimeException("Unhandled value holder: $vh")
        }
    }


}

