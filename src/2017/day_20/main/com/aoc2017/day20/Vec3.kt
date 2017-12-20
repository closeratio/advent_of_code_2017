package com.aoc2017.day20

import kotlin.math.absoluteValue
import kotlin.math.pow

class Vec3(
        x: Number = 0,
        y: Number = 0,
        z: Number = 0) {

    val x: Long = x.toLong()
    val y: Long = y.toLong()
    val z: Long = z.toLong()

    override fun equals(other: Any?): Boolean {
        val v = other as? Vec3 ?: return false
        return x == v.x && y == v.y && z == v.z
    }

    override fun hashCode(): Int {
        return x.hashCode() + y.hashCode() + z.hashCode()
    }

    override fun toString(): String {
        return "Vec3($x, $y, $z)"
    }

    fun length(): Double {
        return (x.toDouble().pow(2)
                + y.toDouble().pow(2)
                + z.toDouble().pow(2)).pow(0.5)
    }

    fun manhattanLength(): Long {
        return x.absoluteValue + y.absoluteValue + z.absoluteValue
    }

    companion object {
        val ZERO = Vec3(0, 0, 0)
    }

    operator fun plus(v: Vec3): Vec3 {
        return Vec3(x + v.x, y + v.y, z + v.z)
    }

}