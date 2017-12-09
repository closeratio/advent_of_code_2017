package com.aoc2017.day8

import com.aoc2017.day8.Condition.*
import com.aoc2017.day8.Operation.DECREMENT
import com.aoc2017.day8.Operation.INCREMENT

object InstructionProcessor {

    private fun ParseOperation(line: String): Operation {
        return when (line.trim()) {
            "inc" -> INCREMENT
            "dec" -> DECREMENT
            else -> throw BadInstructionFormatException(line)
        }
    }

    private fun ParseCondition(line: String): Condition {
        return when (line.trim()) {
            "<" -> LESS_THAN
            "<=" -> LESS_THAN_OR_EQUAL
            "==" -> EQUAL_TO
            ">=" -> GREATER_THAN_OR_EQUAL
            ">" -> GREATER_THAN
            "!=" -> NOT_EQUAL_TO
            else -> throw BadInstructionFormatException(line)
        }
    }

    fun DecodeLine(line: String): Instruction {
        val instRegex = Regex("^(\\w+) (\\w+) (-?\\d+) if (\\w+) ([<=>!]+) (-?\\d+)$")
        val groups = instRegex.matchEntire(line.trim())?.groupValues ?: throw BadInstructionFormatException(line)

        return Instruction(
                groups[1],
                ParseOperation(groups[2]),
                groups[3].toInt(),
                groups[4],
                ParseCondition(groups[5]),
                groups[6].toInt())
    }

    fun TestCondition(reg: Register, condition: Condition, value: Int): Boolean {
        return when(condition) {
            EQUAL_TO -> reg.value == value
            NOT_EQUAL_TO -> reg.value != value
            GREATER_THAN_OR_EQUAL -> reg.value >= value
            GREATER_THAN -> reg.value > value
            LESS_THAN_OR_EQUAL -> reg.value <= value
            LESS_THAN -> reg.value < value
            else -> throw RuntimeException("Unhandled condition: $condition")
        }
    }

    fun PerformOperation(reg: Register, operation: Operation, value: Int) {
        when (operation) {
            INCREMENT -> reg.value += value
            DECREMENT -> reg.value -= value
            else -> throw RuntimeException("Unhandled operation: $operation")
        }
    }

    fun ExecuteInstruction(instr: Instruction, programState: ProgramState) {

        // Get target register
        val targReg = programState.registerMap.getOrPut(
                instr.targRegister,
                { Register(instr.targRegister, 0) })

        // Get cond register
        val condReg = programState.registerMap.getOrPut(
                instr.condRegister,
                { Register(instr.condRegister, 0) })

        if (TestCondition(condReg, instr.cond, instr.condVal)) {
            PerformOperation(targReg, instr.oper, instr.operVal)
        }

        // Update highest value
        val max = programState.registerMap.values.map { it.value }.max()!!
        if (max > programState.highestValue) {
            programState.highestValue = max
        }
    }

}

