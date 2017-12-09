package com.aoc2017.day8

class ProgramState(
        val registerMap: MutableMap<String, Register> = HashMap(),
        var highestValue: Int = 0
) {

    override fun equals(other: Any?): Boolean {
        val ps = other as? ProgramState ?: return false
        return registerMap == ps.registerMap && highestValue == ps.highestValue
    }

    override fun hashCode(): Int {
        return registerMap.hashCode() * highestValue.hashCode()
    }

    override fun toString(): String {
        return "Program state $registerMap"
    }
}