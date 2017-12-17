package com.aoc2017.day16

import com.aoc2017.day16.instructions.Instruction

class ProgramSet(
        progCount: Int
) {

    var programs: String = IntRange(0, progCount - 1)
            .map { ('a'.toInt() + it).toChar().toString() }
            .joinToString("")

    fun executeInstructions(inst: List<Instruction>) {
        inst.forEach { programs = it.execute(this) }
    }

    fun getState(): String {
        return programs
    }

}