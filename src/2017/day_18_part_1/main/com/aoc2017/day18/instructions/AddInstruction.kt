package com.aoc2017.day18.instructions

import com.aoc2017.day18.Register
import com.aoc2017.day18.ValueHolder

class AddInstruction(
        val reg: Register,
        val value: ValueHolder)
    : Instruction() {

    override fun equals(other: Any?): Boolean {
        val inst = other as? AddInstruction ?: return false
        return reg == inst.reg && value == inst.value
    }

    override fun hashCode(): Int {
        return reg.hashCode() * value.hashCode()
    }

    override fun toString(): String {
        return "Add instruction($reg, $value)"
    }

    override fun getSourceRep(): String {
        return "add ${reg.getSourceRep()} ${value.getSourceRep()}"
    }
}