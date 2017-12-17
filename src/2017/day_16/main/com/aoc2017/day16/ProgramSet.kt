package com.aoc2017.day16

import com.aoc2017.day16.instructions.Instruction

class ProgramSet(
        progCount: Int
) {

    val stateCache = HashMap<Pair<Instruction, List<String>>, List<String>>()

    var programs: List<String> = IntRange(0, progCount - 1)
            .map { ('a'.toInt() + it).toChar().toString() }

    fun executeInstruction(inst: Instruction) {
        val pair = Pair(inst, programs)
        val result = stateCache[pair]

        if (result != null) {
            programs = result
        } else {
            programs = inst.execute(this)
            stateCache[pair] = programs
        }
    }

    fun getCachedState(inst: Instruction): String? {
        val pair = Pair(inst, programs)
        val result = stateCache[pair]

        return if (result != null) {
             result.joinToString("") { it }
        } else {
            null
        }
    }

    fun getState(): String {
        return programs.joinToString("") { it }
    }

}