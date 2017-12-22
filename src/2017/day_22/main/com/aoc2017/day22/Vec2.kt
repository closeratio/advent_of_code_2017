package com.aoc2017.day22

class Vec2(
        x: Number,
        y: Number
) {

    val x: Int = x.toInt()
    val y: Int = y.toInt()

    fun up(): Vec2 {
        return Vec2(x, y + 1)
    }

    fun down(): Vec2 {
        return Vec2(x, y - 1)
    }

    fun left(): Vec2 {
        return Vec2(x - 1, y)
    }

    fun right(): Vec2 {
        return Vec2(x + 1, y)
    }

    fun getAdjacent(dir: Direction): Vec2 {
        return when (dir) {
            Direction.UP -> up()
            Direction.DOWN -> down()
            Direction.LEFT -> left()
            Direction.RIGHT -> right()
            else -> throw RuntimeException("Unhandled direction: $dir")
        }
    }

    override fun equals(other: Any?): Boolean {
        val v = other as? Vec2 ?: return false
        return x == v.x && y == v.y
    }

    override fun hashCode(): Int {
        return x.hashCode() + y.hashCode()
    }

    override fun toString(): String {
        return "Vec2($x, $y)"
    }

    companion object {
        val ZERO = Vec2(0, 0)
    }
}