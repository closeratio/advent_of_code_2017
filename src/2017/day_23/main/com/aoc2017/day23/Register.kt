package com.aoc2017.day23

class Register(
        val name: String
): ValueHolder() {

    override fun getValue(machine: Machine): Long {
        return machine.getRegVal(name)
    }

    override fun equals(other: Any?): Boolean {
        val reg = other as? Register ?: return false
        return name == reg.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "Register \"$name\""
    }
}