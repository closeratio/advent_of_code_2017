package com.aoc2017.day8

enum class Operation(private val strVal: String) {
    INCREMENT("inc"),
    DECREMENT("dec");

    override fun toString(): String {
        return strVal
    }
}