package com.aoc2017.day2

object SpreadsheetCalculator {

    private fun GetDigits(line: String): List<Int> {
        return line.trim().split(Regex("\\s+"))
                .filter { it.isNotBlank() }
                .map { Integer.parseInt(it) }
    }

    fun CalculateLineChecksum(line: String): Int {
        val digits = GetDigits(line)
        if (digits.isEmpty() || digits.size < 2) return 0

        return digits.max()!! - digits.min()!!
    }

    fun CalculateLineDivisor(line: String): Int {
        val digits = GetDigits(line)

        digits.forEachIndexed { i1, d1 ->
            digits.filterIndexed { index, _ -> index != i1 }
                    .filter { d1 % it == 0 }
                    .forEach { return d1 / it }
        }

        return 0
    }

    fun CalculateFunctionSum(data: String, hof: (String) -> Int): Int {
        return data.split("\n")
                .map { hof(it) }
                .sum()
    }

}