package com.aoc2017.day18

class LongValue(
        val value: Long
): ValueHolder() {

    override fun getVal(): Long {
        return value
    }

    override fun equals(other: Any?): Boolean {
        val v = other as? LongValue ?: return false
        return value == v.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "Int value ($value)"
    }

    override fun getSourceRep(): String {
        return value.toString()
    }
}