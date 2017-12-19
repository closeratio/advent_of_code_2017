package com.aoc2017.day19

import com.aoc2017.day19.Direction.*

class Vec2(
        val x: Int = 0,
        val y: Int = 0) {

    override fun equals(other: Any?): Boolean {
        val v = other as? Vec2 ?: return false
        return x == v.x && y == v.y
    }

    override fun hashCode(): Int {
        return x.hashCode() + y.hashCode()
    }

    override fun toString(): String {
        return "Vec2 ($x, $y)"
    }

    fun getAdjacent(direction: Direction): Vec2 {
        return when (direction) {
            UP -> Vec2(x, y + 1)
            DOWN -> Vec2(x, y - 1)
            LEFT -> Vec2(x - 1, y)
            RIGHT -> Vec2(x + 1, y)
            else -> throw RuntimeException("Unhandled direction: $direction")
        }
    }
}