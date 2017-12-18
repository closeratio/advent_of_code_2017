package com.aoc2017.day18_part2.instructions

import com.aoc2017.day18_part2.Register
import com.aoc2017.day18_part2.ValueHolder

class SetInstruction(
        val reg: Register,
        val value: ValueHolder)
    : Instruction() {

    override fun equals(other: Any?): Boolean {
        val inst = other as? SetInstruction ?: return false
        return reg == inst.reg && value == inst.value
    }

    override fun hashCode(): Int {
        return reg.hashCode() * value.hashCode()
    }

    override fun toString(): String {
        return "Set instruction($reg, $value)"
    }

    override fun getSourceRep(): String {
        return "set ${reg.getSourceRep()} ${value.getSourceRep()}"
    }
}