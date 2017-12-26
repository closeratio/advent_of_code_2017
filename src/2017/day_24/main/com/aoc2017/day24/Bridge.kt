package com.aoc2017.day24

class Bridge(val components: List<Component>) {

    fun getStrength(): Int {
        return components
                .map { it.port1 + it.port2 }
                .sum()
    }

    override fun equals(other: Any?): Boolean {
        val b = other as? Bridge ?: return false
        return components == b.components
    }

    override fun hashCode(): Int {
        return components.hashCode()
    }

    override fun toString(): String {
        return "Bridge $components"
    }
}