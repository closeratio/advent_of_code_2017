package com.aoc2017.day16.instructions

import com.aoc2017.day16.ProgramSet

class SpinInstruction(
        val count: Int
): Instruction() {

    override fun execute(ps: ProgramSet): List<String> {
        val progs = ps.programs
        return (progs.drop(progs.size - count) + progs.take(progs.size - count)).toList()
    }

    override fun equals(other: Any?): Boolean {
        val inst = other as? SpinInstruction ?: return false
        return count == inst.count
    }

    override fun hashCode(): Int {
        return count.hashCode()
    }

    override fun toString(): String {
        return "Spin instruction($count)"
    }
}