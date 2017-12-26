package com.aoc2017.day24

class Component(
        val port1: Int,
        val port2: Int) {

    override fun equals(other: Any?): Boolean {
        val c = other as? Component ?: return false
        return port1 == c.port1 && port2 == c.port2
    }

    override fun hashCode(): Int {
        return port1.hashCode() + port2.hashCode()
    }

    override fun toString(): String {
        return "Component ($port1, $port2)"
    }
}