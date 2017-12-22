package com.aoc2017.day22

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun left(): Direction {
        return when (this) {
            UP -> LEFT
            DOWN -> RIGHT
            LEFT -> DOWN
            RIGHT -> UP
            else -> throw RuntimeException("Unhandled direction: $this")
        }
    }

    fun right(): Direction {
        return when (this) {
            UP -> RIGHT
            DOWN -> LEFT
            LEFT -> UP
            RIGHT -> DOWN
            else -> throw RuntimeException("Unhandled direction: $this")
        }
    }

    fun opposite(): Direction {
        return when (this) {
            UP -> DOWN
            DOWN -> UP
            LEFT -> RIGHT
            RIGHT -> LEFT
            else -> throw RuntimeException("Unhandled direction: $this")
        }
    }
}