package com.aoc2017.day18_part2

import com.aoc2017.day18_part2.instructions.*
import java.io.IOException

object Compiler {

    private val INST_REGEX = Regex("^([a-z]+) (\\w+)( -?\\w+)?$")

    private val NUM_REGEX = Regex("-?\\d+")
    private val REG_REGEX = Regex("[a-z]+")

    fun compileLine(line: String): Instruction {
        val result = INST_REGEX.find(line) ?: throw IOException("Bad instruction format: $line")

        val gVals = result.groupValues

        val instName = gVals[1].toLowerCase()
        val target = gVals[2]
        val modifier = if (gVals.size >= 4) gVals[3].trim() else null

        return when (instName) {
            "set" -> SetInstruction(getRegister(line, target), getModifier(line, modifier))
            "add" -> AddInstruction(getRegister(line, target), getModifier(line, modifier))
            "mul" -> MultiplyInstruction(getRegister(line, target), getModifier(line, modifier))
            "mod" -> ModuloInstruction(getRegister(line, target), getModifier(line, modifier))
            "snd" -> SendInstruction(getValueHolder(target))
            "rcv" -> ReceiveInstruction(getRegister(line, target))
            "jgz" -> JumpInstruction(getValueHolder(target), getModifier(line, modifier))
            else -> throw IOException("Unhandled instruction: $instName")
        }
    }

    private fun getModifier(line: String, modifier: String?): ValueHolder {
        if (modifier == null) throw IOException("Incomplete instruction: $line")
        return getValueHolder(modifier)
    }

    private fun getRegister(line: String, target: String): Register {
        if (!REG_REGEX.matches(target)) throw IOException("Bad register: $line")
        return Register(target)
    }

    private fun getValueHolder(str: String): ValueHolder {
        return if (str.matches(NUM_REGEX)) {
            LongValue(str.toLong())
        } else {
            Register(str)
        }
    }


}

