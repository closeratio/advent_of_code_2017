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
        testProgramSet.executeInstructions(listOf(SpinInstruction(1)))
        assertEquals(testProgramSet.getState(), "eabcd")
    }

    @Test
    fun testSpin2() {
        testProgramSet.executeInstructions(listOf(SpinInstruction(3)))
        assertEquals(testProgramSet.getState(), "cdeab")
    }

    @Test
    fun testExchange() {
        testProgramSet.executeInstructions(listOf(ExchangeInstruction(3, 4)))
        assertEquals(testProgramSet.getState(), "abced")
    }

    @Test
    fun testPartner() {
        testProgramSet.executeInstructions(listOf(PartnerInstruction('b', 'd')))
        assertEquals(testProgramSet.getState(), "adcbe")
    }

    @Test
    fun testExecuteInstructions() {
        testProgramSet.executeInstructions(listOf(
                "s1",
                "x3/4",
                "pe/b"
        ).map { InstructionCompiler.compileInstruction(it) })

        assertEquals(testProgramSet.getState(), "baedc")
    }

    @Test
    fun testActualExecuteInstructions() {
        val instructions = File(javaClass.getResource("/input.txt").toURI())
                .readText()
                .split(",")
                .map { it.trim() }

        val progSet = ProgramSet(16)
        progSet.executeInstructions(instructions.map { InstructionCompiler.compileInstruction(it) })

        println(progSet.getState())

        // Only added once answer for part 1 was found and verified
        assertEquals(progSet.getState(), "nlciboghjmfdapek")
    }

    @Test
    fun testExecuteInstructionsMultiple() {
        testProgramSet.executeInstructions(listOf(
                "s1",
                "x3/4",
                "pe/b",
                "s1",
                "x3/4",
                "pe/b"
        ).map { InstructionCompiler.compileInstruction(it) })

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

        val sequence = mutableListOf<String>()

        val times = 1_000_000_000
        repeat(times) {
            if (progSet.getState() in sequence) {
                println(sequence[times % sequence.size])
                return
            } else {
                sequence.add(progSet.getState())
                progSet.executeInstructions(instructions)
            }
        }



    }

}