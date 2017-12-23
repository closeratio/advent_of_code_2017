package com.aoc2017.day23

import com.aoc2017.day23.instructions.*

class Machine {

    var reg_a = 0L
    var reg_b = 0L
    var reg_c = 0L
    var reg_d = 0L
    var reg_e = 0L
    var reg_f = 0L
    var reg_g = 0L
    var reg_h = 0L

    var instructionIndex = 0L
    var mulCount = 0L

    fun executeInstructions(instructions: List<Instruction>) {
        while (instructionIndex >= 0 && instructionIndex < instructions.size) {
            executeInstruction(instructions[instructionIndex.toInt()])
        }
    }

    private fun executeInstruction(inst: Instruction) {
        when (inst) {
            is SetInstruction -> executeSetInstruction(inst)
            is MulInstruction -> executeMulInstruction(inst)
            is SubInstruction -> executeSubInstruction(inst)
            is JnzInstruction -> executeJnzInstruction(inst)
            else -> throw RuntimeException("Unhandled instruction type: ${inst.javaClass.simpleName}")
        }
    }

    private fun executeSetInstruction(inst: SetInstruction) {
        setRegVal(inst.reg.name, getValue(inst.value))
        ++instructionIndex
    }

    private fun executeMulInstruction(inst: MulInstruction) {
        ++mulCount
        setRegVal(inst.reg.name, getRegVal(inst.reg.name) * getValue(inst.value))
        ++instructionIndex
    }

    private fun executeSubInstruction(inst: SubInstruction) {
        setRegVal(inst.reg.name, getRegVal(inst.reg.name) - getValue(inst.value))
        ++instructionIndex
    }

    private fun executeJnzInstruction(inst: JnzInstruction) {
        val testVal = getValue(inst.test)

        if (testVal != 0L) {
            instructionIndex += getValue(inst.amount)
        } else {
            ++instructionIndex
        }
    }

    private fun getValue(holder: ValueHolder): Long {
        return when(holder) {
            is Register -> getRegVal(holder.name)
            is LongValue -> holder.value
            else -> throw RuntimeException("Unhandled value holder: $holder")
        }
    }

    fun getRegVal(name: String): Long {
        return when (name) {
            "a" -> reg_a
            "b" -> reg_b
            "c" -> reg_c
            "d" -> reg_d
            "e" -> reg_e
            "f" -> reg_f
            "g" -> reg_g
            "h" -> reg_h
            else -> throw RuntimeException("Bad register: $name")
        }
    }

    private fun setRegVal(name: String, value: Long) {
        when (name) {
            "a" -> reg_a = value
            "b" -> reg_b = value
            "c" -> reg_c = value
            "d" -> reg_d = value
            "e" -> reg_e = value
            "f" -> reg_f = value
            "g" -> reg_g = value
            "h" -> reg_h = value
            else -> throw RuntimeException("Bad register: $name")
        }
    }

}