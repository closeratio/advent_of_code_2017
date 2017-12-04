package com.aoc2017.day3

class Extents(
        val left: Int = 0,
        val right: Int = 0,
        val up: Int = 0,
        val down: Int = 0) {

    fun extend(vec: Vec2): Extents {
        return Extents(
                if (left > vec.x) vec.x else left,
                if (right < vec.x) vec.x else right,
                if (up < vec.y) vec.y else up,
                if (down > vec.y) vec.y else down)
    }

}