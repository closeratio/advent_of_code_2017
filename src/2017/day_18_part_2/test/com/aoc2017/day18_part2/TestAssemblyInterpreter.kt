package com.aoc2017.day18_part2

import com.aoc2017.day18_part2.Compiler.compileLine
import com.aoc2017.day18_part2.instructions.*
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class TestAssemblyInterpreter {

    val TEST_INSTRUCTIONS = arrayOf(
            "set a 1",
            "add a 2",
            "mul a a",
            "mod a 5",
            "snd a",
            "set a 0",
            "rcv a",
            "jgz a -1",
            "set a 1",
            "jgz a -2")

    @Test
    fun testCompileInstructions() {
        val testReg = Register("a")

        assertEquals(compileLine("set a 1"), SetInstruction(testReg, LongValue(1)))
        assertEquals(compileLine("add a 2"), AddInstruction(testReg, LongValue(2)))
        assertEquals(compileLine("mul a a"), MultiplyInstruction(testReg, testReg))
        assertEquals(compileLine("mod a 5"), ModuloInstruction(testReg, LongValue(5)))
        assertEquals(compileLine("snd a"), SendInstruction(testReg))
        assertEquals(compileLine("rcv a"), ReceiveInstruction(testReg))
        assertEquals(compileLine("jgz a -1"), JumpInstruction(testReg, LongValue(-1)))
    }

    @Test
    fun testCompileTestInstructions() {
        TEST_INSTRUCTIONS.forEach { compileLine(it) }
    }

    @Test
    fun testCompileActualInstructions() {
        javaClass.getResource("/input.txt")
                .readText()
                .trim()
                .split("\n")
                .map { it.trim() }
                .forEach {
                    val inst = compileLine(it)
                    val src = inst.getSourceRep()
                    assertEquals(src, it)
                }
    }

    @Test
    fun testRunActualInstructions() {
        val instructions = javaClass.getResource("/input.txt")
                .readText()
                .trim()
                .split("\n")
                .map { it.trim() }
                .map { compileLine(it) }

        val host = ProgramHost()
        host.executeInstructions(instructions)

        println(host.prog1.sendCount)
    }



}

