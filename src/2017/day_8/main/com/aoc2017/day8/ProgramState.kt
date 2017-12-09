package com.aoc2017.day8

class ProgramState(val registerMap: MutableMap<String, Register> = HashMap()) {

    override fun equals(other: Any?): Boolean {
        val ps = other as? ProgramState ?: return false
        return registerMap == ps.registerMap
    }

    override fun hashCode(): Int {
        return registerMap.hashCode()
    }

    override fun toString(): String {
        return "Program state $registerMap"
    }
}