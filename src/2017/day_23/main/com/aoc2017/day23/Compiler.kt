package com.aoc2017.day23

import com.aoc2017.day23.instructions.*
import java.io.IOException

object Compiler {

    val INSTR_REGEX = Regex("^([a-z]+) (-?\\w+) (-?\\w+)$")
    val NUM_REGEX = Regex("-?\\d+")

    fun compile(instrString: String): List<Instruction> {
        return instrString
                .trim()
                .split("\n")
                .map { compileLine(it.trim()) }
    }

    fun compileLine(line: String): Instruction {
        val result = INSTR_REGEX.matchEntire(line) ?: throw IOException("Bad instruction format: $line")
        val instString = result.groupValues[1]
        val target = result.groupValues[2]
        val modifier = result.groupValues[3]

        return when(instString) {
            "set" -> SetInstruction(Register(target), getValueHolder(modifier))
            "sub" -> SubInstruction(Register(target), getValueHolder(modifier))
            "mul" -> MulInstruction(Register(target), getValueHolder(modifier))
            "jnz" -> JnzInstruction(getValueHolder(target), getValueHolder(modifier))
            else -> throw IOException("Unrecognised instruction: $instString")
        }
    }

    fun getValueHolder(line: String): ValueHolder {
        return if (NUM_REGEX.matchEntire(line) != null) {
            IntValue(line.toInt())
        } else {
            Register(line)
        }
    }

}