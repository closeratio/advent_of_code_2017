package com.aoc2017.day18

import com.aoc2017.day18.Compiler.compileLine
import com.aoc2017.day18.instructions.*
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class TestAssemblyInterpreter {

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

}

