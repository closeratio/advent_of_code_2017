package com.aoc2017.day18_part1.instructions

import com.aoc2017.day18_part1.ValueHolder

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

    override fun getSourceRep(): String {
        return "snd ${valueHolder.getSourceRep()}"
    }
}