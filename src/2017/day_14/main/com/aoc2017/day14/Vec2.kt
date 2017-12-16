package com.aoc2017.day14

class Vec2(
        val x: Int,
        val y: Int
) {

    fun getAdjacent(): List<Vec2> {
        return listOf(right(), left(), up(), down()        )
    }

    fun right(): Vec2 {
        return Vec2(x + 1, y)
    }

    fun left(): Vec2 {
        return Vec2(x - 1, y)
    }

    fun up(): Vec2 {
        return Vec2(x, y - 1)
    }

    fun down(): Vec2 {
        return Vec2(x, y + 1)
    }

    override fun equals(other: Any?): Boolean {
        val v = other as? Vec2 ?: return false
        return x == v.x && y == v.y
    }

    override fun hashCode(): Int {
        return x.hashCode() * y.hashCode()
    }

    override fun toString(): String {
        return "Vec2($x, $y)"
    }
}