package com.aoc2017.day18

class IntValue(
        val value: Int
): ValueHolder() {

    override fun getVal(): Int {
        return value
    }

    override fun equals(other: Any?): Boolean {
        val v = other as? IntValue ?: return false
        return value == v.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "Int value ($value)"
    }
}