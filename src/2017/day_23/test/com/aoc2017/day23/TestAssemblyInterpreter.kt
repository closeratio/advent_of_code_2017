package com.aoc2017.day23

import com.aoc2017.day23.instructions.JnzInstruction
import com.aoc2017.day23.instructions.MulInstruction
import com.aoc2017.day23.instructions.SetInstruction
import com.aoc2017.day23.instructions.SubInstruction
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class TestAssemblyInterpreter {

    val INPUT_INSTRUCTIONS = javaClass.getResource("/input.txt")
            .readText()
            .trim()

    @Test
    fun testCompileInstructions() {
        val instructions = Compiler.compile(INPUT_INSTRUCTIONS)

        assertEquals(instructions[0], SetInstruction(Register("b"), IntValue(81)))
        assertEquals(instructions[1], SetInstruction(Register("c"), Register("b")))

        assertEquals(instructions[2], JnzInstruction(Register("a"), IntValue(2)))

        assertEquals(instructions[4], MulInstruction(Register("b"), IntValue(100)))

        assertEquals(instructions[5], SubInstruction(Register("b"), IntValue(-100000)))
    }

}