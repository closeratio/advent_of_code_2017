package com.aoc2017.day16.instructions

import com.aoc2017.day16.ProgramSet

class ExchangeInstruction(
        val ind1: Int,
        val ind2: Int
) : Instruction() {

    override fun execute(ps: ProgramSet): List<String> {
        val progs = ps.programs.toMutableList()
        val temp = progs[ind1]
        progs[ind1] = progs[ind2]
        progs[ind2] = temp
        return progs.toList()
    }

    override fun equals(other: Any?): Boolean {
        val inst = other as? ExchangeInstruction ?: return false
        return ind1 == inst.ind1 && ind2 == inst.ind2
    }

    override fun hashCode(): Int {
        return (ind1 + 1).hashCode() * (ind2 + 1).hashCode()
    }

    override fun toString(): String {
        return "Exchange instruction ($ind1, $ind2)"
    }

}