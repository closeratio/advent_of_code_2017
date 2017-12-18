package com.aoc2017.day18

class Register(
        val name: String
): ValueHolder() {

    var value = 0

    override fun getVal(): Int {
        return value
    }

    override fun equals(other: Any?): Boolean {
        val reg = other as? Register ?: return false
        return name == reg.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "Register $name ($value)"
    }
}