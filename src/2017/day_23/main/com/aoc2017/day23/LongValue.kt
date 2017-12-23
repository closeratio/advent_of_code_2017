package com.aoc2017.day23

class LongValue(
        val value: Long
): ValueHolder() {

    override fun getValue(machine: Machine): Long {
        return value
    }

    override fun equals(other: Any?): Boolean {
        val iv = other as? LongValue ?: return false
        return value == iv.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "Int value ($value)"
    }
}