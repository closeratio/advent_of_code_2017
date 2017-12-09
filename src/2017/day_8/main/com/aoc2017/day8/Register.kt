package com.aoc2017.day8

class Register(val name: String, var value: Int = 0) {

    override fun equals(other: Any?): Boolean {
        val reg = other as? Register ?: return false
        return name == reg.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "Register [$name $value]"
    }
}