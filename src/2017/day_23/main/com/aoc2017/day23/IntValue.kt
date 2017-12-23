package com.aoc2017.day23

class IntValue(
        val value: Int
): ValueHolder() {

    override fun equals(other: Any?): Boolean {
        val iv = other as? IntValue ?: return false
        return value == iv.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "Int value ($value)"
    }
}