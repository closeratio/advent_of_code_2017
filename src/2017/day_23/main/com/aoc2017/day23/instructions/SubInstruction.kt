package com.aoc2017.day23.instructions

import com.aoc2017.day23.Register
import com.aoc2017.day23.ValueHolder

class SubInstruction(
        val reg: Register,
        val value: ValueHolder
): Instruction() {

    override fun equals(other: Any?): Boolean {
        val inst = other as? SubInstruction ?: return false
        return reg == inst.reg && value == inst.value
    }

    override fun hashCode(): Int {
        return reg.hashCode() + value.hashCode()
    }

    override fun toString(): String {
        return "Sub instruction ($reg, $value)"
    }
}