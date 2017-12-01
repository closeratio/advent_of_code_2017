package com.aoc2017.day1

object DigitParser {

    fun parseDigits(text: String): Int {
        val digits = text.trim()
                .map { it.toString().toInt() }
                .toMutableList()

        if (digits.size < 2) return 0
        digits += digits[0]

        return digits
                .windowed(2, 1)
                .filter { it[0] == it[1] }
                .map { it[0] }
                .sum()
    }

    fun parseDigitsComplex(text: String): Int {
        val digits = text.trim()
                .map { it.toString().toInt() }
                .toMutableList()

        if (digits.size < 2) return 0
        val size = digits.size

        return digits
                .mapIndexed { index, it -> arrayOf(it, digits[(index + size / 2) % size]) }
                .filter { it[0] == it[1] }
                .map { it[0] }
                .sum()
    }

}