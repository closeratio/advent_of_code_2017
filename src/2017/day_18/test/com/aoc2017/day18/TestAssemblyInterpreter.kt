package com.aoc2017.day18

import com.aoc2017.day18.Compiler.compileLine
import com.aoc2017.day18.instructions.*
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

        assertEquals(compileLine("set a 1"), SetInstruction(testReg, IntValue(1)))
        assertEquals(compileLine("add a 2"), AddInstruction(testReg, IntValue(2)))
        assertEquals(compileLine("mul a a"), MultiplyInstruction(testReg, testReg))
        assertEquals(compileLine("mod a 5"), ModuloInstruction(testReg, IntValue(5)))
        assertEquals(compileLine("snd a"), SoundInstruction(testReg))
        assertEquals(compileLine("rcv a"), RecoverInstruction(testReg))
        assertEquals(compileLine("jgz a -1"), JumpInstruction(testReg, IntValue(-1)))
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
                .forEach { compileLine(it) }
    }

    @Test
    fun testInstructionExecution() {
        val instructions = TEST_INSTRUCTIONS.map { compileLine(it) }

        val machine = Machine()

        machine.executeInstruction(instructions[0])
        assertEquals(machine.currInstIndex, 1)
        assertEquals(machine.registers["a"]!!.value, 1)

        machine.executeInstruction(instructions[1])
        assertEquals(machine.currInstIndex, 2)
        assertEquals(machine.registers["a"]!!.value, 3)

        machine.executeInstruction(instructions[2])
        assertEquals(machine.currInstIndex, 3)
        assertEquals(machine.registers["a"]!!.value, 9)

        machine.executeInstruction(instructions[3])
        assertEquals(machine.currInstIndex, 4)
        assertEquals(machine.registers["a"]!!.value, 4)

        assertEquals(machine.soundRegister.value, 0)
        machine.executeInstruction(instructions[4])
        assertEquals(machine.currInstIndex, 5)
        assertEquals(machine.registers["a"]!!.value, 4)
        assertEquals(machine.soundRegister.value, 4)

        machine.executeInstruction(instructions[5])
        assertEquals(machine.currInstIndex, 6)
        assertEquals(machine.registers["a"]!!.value, 0)

        machine.executeInstruction(instructions[6])
        assertEquals(machine.currInstIndex, 7)

        machine.executeInstruction(instructions[7])
        assertEquals(machine.currInstIndex, 8)

        machine.executeInstruction(instructions[8])
        assertEquals(machine.registers["a"]!!.value, 1)
        assertEquals(machine.currInstIndex, 9)

        machine.executeInstruction(instructions[9])
        assertEquals(machine.currInstIndex, 7)

        machine.executeInstruction(instructions[6])
        assertEquals(machine.registers["a"]!!.value, 4)
        assertEquals(machine.currInstIndex, 8)
    }

    @Test
    fun testRunActualInstructions() {
        val instructions = javaClass.getResource("/input.txt")
                .readText()
                .trim()
                .split("\n")
                .map { it.trim() }
                .map { compileLine(it) }

        val machine = Machine()
        machine.executeAllInstructions(instructions)
    }



}

