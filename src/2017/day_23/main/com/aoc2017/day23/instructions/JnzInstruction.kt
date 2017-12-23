package com.aoc2017.day23.instructions

import com.aoc2017.day23.ValueHolder

class JnzInstruction(
        val test: ValueHolder,
        val amount: ValueHolder
): Instruction() {

    override fun equals(other: Any?): Boolean {
        val inst = other as? JnzInstruction ?: return false
        return test == inst.test && amount == inst.amount
    }

    override fun hashCode(): Int {
        return test.hashCode() + amount.hashCode()
    }

    override fun toString(): String {
        return "Jnz instruction ($test, $amount)"
    }
}