package com.aoc2017.day8

import com.aoc2017.day8.Condition.*
import com.aoc2017.day8.Operation.*
import org.testng.Assert.*
import org.testng.annotations.Test
import java.io.File

class TestInstructionProcessor {

    private val TEST_LINES = listOf(
            "b inc 5 if a > 1",
            "a inc 1 if b < 5",
            "c dec -10 if a >= 1",
            "c inc -20 if c == 10")

    private val TEST_INSTRUCTIONS = listOf(
            Instruction("b", INCREMENT, 5, "a", GREATER_THAN, 1),
            Instruction("a", INCREMENT, 1, "b", LESS_THAN, 5),
            Instruction("c", DECREMENT, -10, "a", GREATER_THAN_OR_EQUAL, 1),
            Instruction("c", INCREMENT, -20, "c", EQUAL_TO, 10)
    )

    @Test
    fun testParseInstruction1() {
        assertEquals(InstructionProcessor.DecodeLine(TEST_LINES[0]), TEST_INSTRUCTIONS[0])
    }

    @Test
    fun testParseInstruction2() {
        assertEquals(InstructionProcessor.DecodeLine(TEST_LINES[1]), TEST_INSTRUCTIONS[1])
    }

    @Test
    fun testParseInstruction3() {
        assertEquals(InstructionProcessor.DecodeLine(TEST_LINES[2]), TEST_INSTRUCTIONS[2])
    }

    @Test
    fun testParseInstruction4() {
        assertEquals(InstructionProcessor.DecodeLine(TEST_LINES[3]), TEST_INSTRUCTIONS[3])
    }

    @Test
    fun testExecuteInstructions() {
        val regA = Register("a")
        val regB = Register("b")

        val testPS = ProgramState(mapOf(
                regA.name to regA,
                regB.name to regB
        ).toMutableMap())

        val ps = ProgramState()
        InstructionProcessor.ExecuteInstruction(TEST_INSTRUCTIONS[0], ps)
        assertEquals(ps, testPS)

        regA.value = 1
        InstructionProcessor.ExecuteInstruction(TEST_INSTRUCTIONS[1], ps)
        assertEquals(regA.value, ps.registerMap["a"]!!.value)
        assertEquals(regB.value, ps.registerMap["b"]!!.value)

        val regC = Register("c", 10)
        testPS.registerMap["c"] = regC
        InstructionProcessor.ExecuteInstruction(TEST_INSTRUCTIONS[2], ps)
        assertEquals(regC.value, ps.registerMap["c"]!!.value)

        regC.value = -10
        InstructionProcessor.ExecuteInstruction(TEST_INSTRUCTIONS[3], ps)
        assertEquals(regC.value, ps.registerMap["c"]!!.value)
    }

    @Test
    fun testActualInstructions() {
        val lines = File(javaClass.getResource("/input.txt").toURI()).readLines()
        val instructions = lines.map { InstructionProcessor.DecodeLine(it) }

        val ps = ProgramState()

        instructions.forEach { InstructionProcessor.ExecuteInstruction(it, ps) }

        println(ps.registerMap.values.map { it.value }.max())
    }

    @Test
    fun testHighestHeldValue() {
        val ps = ProgramState()
        TEST_INSTRUCTIONS.forEach { InstructionProcessor.ExecuteInstruction(it, ps) }

        assertEquals(ps.highestValue, 10)
    }

    @Test
    fun testActualHighestHeldValue() {
        val lines = File(javaClass.getResource("/input.txt").toURI()).readLines()
        val instructions = lines.map { InstructionProcessor.DecodeLine(it) }

        val ps = ProgramState()

        instructions.forEach { InstructionProcessor.ExecuteInstruction(it, ps) }

        println(ps.highestValue)
    }

}

