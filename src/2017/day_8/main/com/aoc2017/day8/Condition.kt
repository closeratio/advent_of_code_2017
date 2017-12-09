package com.aoc2017.day8

enum class Condition(
        private val strVal: String
) {
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN_OR_EQUAL("<="),
    EQUAL_TO("=="),
    NOT_EQUAL_TO("!=");

    override fun toString(): String {
        return strVal
    }
}