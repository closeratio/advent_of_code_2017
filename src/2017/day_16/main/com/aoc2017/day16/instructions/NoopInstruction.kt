package com.aoc2017.day16.instructions

import com.aoc2017.day16.ProgramSet

object NoopInstruction: Instruction() {

    override fun execute(ps: ProgramSet): String {
        return ps.programs
    }

    override fun equals(other: Any?): Boolean {
        return other == this
    }

    override fun hashCode(): Int {
        return javaClass.simpleName.hashCode()
    }

    override fun toString(): String {
        return "Noop instruction"
    }
}