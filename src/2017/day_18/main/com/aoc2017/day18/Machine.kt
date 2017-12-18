package com.aoc2017.day18

import com.aoc2017.day18.instructions.*

class Machine {

    val registers = mutableMapOf<String, Register>()
    val soundRegister = Register("sound")

    var currInstIndex = 0
    var recovered = false

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
            is SoundInstruction -> executeSoundInstruction(inst)
            is RecoverInstruction -> executeRecoverInstruction(inst)
            is JumpInstruction -> executeJumpInstruction(inst)
            else -> throw RuntimeException("Unhandled instruction: $inst")
        }
    }

    private fun executeJumpInstruction(inst: JumpInstruction) {
        val testVal = getValueHolderVal(inst.test)
        if (testVal > 0) {
            currInstIndex += getValueHolderVal(inst.jumpValue)
        } else {
            currInstIndex++
        }
    }

    private fun executeRecoverInstruction(inst: RecoverInstruction) {
        val reg = getOrPutReg(inst.reg.name)
        if (reg.value > 0) {
            reg.value = soundRegister.value
            println("Recover executed with value ${soundRegister.value}")
            recovered = true
        }
        currInstIndex++
    }

    private fun executeSoundInstruction(inst: SoundInstruction) {
        soundRegister.value = getValueHolderVal(inst.valueHolder)
        currInstIndex++
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

    private fun getValueHolderVal(vh: ValueHolder): Int {
        return when (vh) {
            is Register -> getOrPutReg(vh.name).value
            is IntValue -> vh.value
            else -> throw RuntimeException("Unhandled value holder: $vh")
        }
    }


}

