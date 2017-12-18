package com.aoc2017.day18_part2.instructions

import com.aoc2017.day18_part2.ValueHolder

class JumpInstruction(
        val test: ValueHolder,
        val jumpValue: ValueHolder)
    : Instruction() {

    override fun equals(other: Any?): Boolean {
        val inst = other as? JumpInstruction ?: return false
        return test == inst.test && jumpValue == inst.jumpValue
    }

    override fun hashCode(): Int {
        return test.hashCode() * jumpValue.hashCode()
    }

    override fun toString(): String {
        return "Jump instruction($test, $jumpValue)"
    }

    override fun getSourceRep(): String {
        return "jgz ${test.getSourceRep()} ${jumpValue.getSourceRep()}"
    }
}