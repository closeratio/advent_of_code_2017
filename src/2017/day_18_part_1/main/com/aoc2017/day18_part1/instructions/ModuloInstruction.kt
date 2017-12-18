package com.aoc2017.day18_part1.instructions

import com.aoc2017.day18_part1.Register
import com.aoc2017.day18_part1.ValueHolder

class ModuloInstruction(
        val reg: Register,
        val value: ValueHolder)
    : Instruction() {

    override fun equals(other: Any?): Boolean {
        val inst = other as? ModuloInstruction ?: return false
        return reg == inst.reg && value == inst.value
    }

    override fun hashCode(): Int {
        return reg.hashCode() * value.hashCode()
    }

    override fun toString(): String {
        return "Modulo instruction ($reg, $value)"
    }

    override fun getSourceRep(): String {
        return "mod ${reg.getSourceRep()} ${value.getSourceRep()}"
    }
}