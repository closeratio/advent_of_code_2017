package com.aoc2017.day11

class Vec2(val x: Int = 0, val y: Int = 0) {

    override fun equals(other: Any?): Boolean {
        val vec = other as? Vec2 ?: return false
        return x == vec.x && y == vec.y
    }

    override fun hashCode(): Int {
        return x.hashCode() * y.hashCode()
    }

    override fun toString(): String {
        return "Vec2($x, $y)"
    }
}