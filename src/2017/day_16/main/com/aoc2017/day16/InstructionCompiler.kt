package com.aoc2017.day16

import com.aoc2017.day16.instructions.*
import java.io.IOException

object InstructionCompiler {

    fun compileInstruction(inst: String): Instruction {
        return when (inst[0]) {
            's' -> {
                val ind = inst.substring(1).toInt()
                if (ind > 0) SpinInstruction(ind) else NoopInstruction
            }
            'x' -> {
                val ind = inst.substring(1).split("/").map { it.toInt() }
                if (ind[0] != ind[1]) ExchangeInstruction(ind[0], ind[1]) else NoopInstruction
            }
            'p' -> {
                val names = inst.substring(1).split("/")
                if (names[0] != names[1]) PartnerInstruction(names[0][0], names[1][0]) else NoopInstruction
            }
            else -> throw IOException("Unhandled instruction: $inst")
        }
    }

}