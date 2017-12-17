package com.aoc2017.day16

import com.aoc2017.day16.instructions.Instruction

class ProgramSet(
        progCount: Int
) {

    val stateCache = HashMap<Pair<Instruction, String>, String>()

    var programs: String = IntRange(0, progCount - 1)
            .map { ('a'.toInt() + it).toChar().toString() }
            .joinToString("")

    var newCacheCount = 0

    fun executeInstruction(inst: Instruction): Boolean {
        val pair = Pair(inst, programs)
        val result = stateCache[pair]

        return if (result != null) {
            programs = result
            true
        } else {
            programs = inst.execute(this)
            stateCache[pair] = programs
            newCacheCount++
            false
        }
    }

    fun getState(): String {
        return programs
    }

}