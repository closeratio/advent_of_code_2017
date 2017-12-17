package com.aoc2017.day16

import com.aoc2017.day16.instructions.ExchangeInstruction
import com.aoc2017.day16.instructions.PartnerInstruction
import com.aoc2017.day16.instructions.SpinInstruction
import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.io.File

class TestProgramSet {

    lateinit var testProgramSet: ProgramSet

    @BeforeMethod
    fun beforeMethod() {
        testProgramSet = ProgramSet(5)
    }

    @Test
    fun testSetCreation() {
        assertEquals(testProgramSet.programs.length, 5)
        assertEquals(testProgramSet.programs[0], 'a')
        assertEquals(testProgramSet.programs[1], 'b')
        assertEquals(testProgramSet.programs[2], 'c')
        assertEquals(testProgramSet.programs[3], 'd')
        assertEquals(testProgramSet.programs[4], 'e')
    }

    @Test
    fun testSpin1() {
        testProgramSet.executeInstruction(SpinInstruction(1))
        assertEquals(testProgramSet.getState(), "eabcd")
    }

    @Test
    fun testSpin2() {
        testProgramSet.executeInstruction(SpinInstruction(3))
        assertEquals(testProgramSet.getState(), "cdeab")
    }

    @Test
    fun testExchange() {
        testProgramSet.executeInstruction(ExchangeInstruction(3, 4))
        assertEquals(testProgramSet.getState(), "abced")
    }

    @Test
    fun testPartner() {
        testProgramSet.executeInstruction(PartnerInstruction('b', 'd'))
        assertEquals(testProgramSet.getState(), "adcbe")
    }

    @Test
    fun testExecuteInstructions() {
        listOf(
                "s1",
                "x3/4",
                "pe/b"
        ).map { testProgramSet.executeInstruction(InstructionCompiler.compileInstruction(it)) }

        assertEquals(testProgramSet.getState(), "baedc")
    }

    @Test
    fun testActualExecuteInstructions() {
        val instructions = File(javaClass.getResource("/input.txt").toURI())
                .readText()
                .split(",")
                .map { it.trim() }

        val progSet = ProgramSet(16)
        instructions.forEach { progSet.executeInstruction(InstructionCompiler.compileInstruction(it)) }

        println(progSet.getState())
    }

    @Test
    fun testExecuteInstructionsMultiple() {
        listOf(
                "s1",
                "x3/4",
                "pe/b",
                "s1",
                "x3/4",
                "pe/b"
        ).forEach { testProgramSet.executeInstruction(InstructionCompiler.compileInstruction(it)) }

        assertEquals(testProgramSet.getState(), "ceadb")
    }

    @Test
    fun testActualExecuteInstructionsMultiple() {
        val progSet = ProgramSet(16)
        val instructions = File(javaClass.getResource("/input.txt").toURI())
                .readText()
                .split(",")
                .map { it.trim() }
                .map { InstructionCompiler.compileInstruction(it) }

        var iterCount = 0
        do {
            var loop = false
            for (inst in instructions) {
                iterCount++
                if (progSet.executeInstruction(inst)) {
                    loop = true

                    break
                }
            }
        } while (!loop)

        // work out loop length

        println(iterCount)

    }

}