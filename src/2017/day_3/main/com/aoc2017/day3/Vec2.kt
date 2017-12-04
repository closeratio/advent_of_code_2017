package com.aoc2017.day3

class Vec2(val x: Int = 0, val y: Int = 0) {

    override fun equals(other: Any?): Boolean {
        val vec = other as? Vec2 ?: return false

        return vec.x == x && vec.y == y
    }

    override fun hashCode(): Int {
        return x.hashCode() * y.hashCode()
    }

    fun right(): Vec2 {
        return Vec2(x + 1, y)
    }

    fun left(): Vec2 {
        return Vec2(x - 1, y)
    }

    fun up(): Vec2 {
        return Vec2(x, y + 1)
    }

    fun down(): Vec2 {
        return Vec2(x, y - 1)
    }

    override fun toString(): String {
        return "Vec2($x, $y)"
    }

    fun manDistance(): Int {
        return Math.abs(x) + Math.abs(y)
    }

    fun adjacentPositions(): List<Vec2> {
        return listOf(
                up(),
                up().right(),
                right(),
                right().down(),
                down(),
                down().left(),
                left(),
                left().up()
        )
    }
}