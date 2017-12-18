package com.aoc2017.day18.instructions

import com.aoc2017.day18.ValueHolder

class SoundInstruction(
        val valueHolder: ValueHolder)
    : Instruction() {

    override fun equals(other: Any?): Boolean {
        val inst = other as? SoundInstruction ?: return false
        return valueHolder == inst.valueHolder
    }

    override fun hashCode(): Int {
        return valueHolder.hashCode()
    }

    override fun toString(): String {
        return "Sound instruction ($valueHolder)"
    }
}