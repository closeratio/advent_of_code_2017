package com.aoc2017.day21

import java.io.IOException

object InputParser {

    fun parseState(data: String): Array<Array<Boolean>> {
        return mapLine(data.trim().replace("\n", "/"))
    }

    fun parseRule(line: String): EnhancementRule {
        val split = line.split("=>").map { it.trim() }
        val input = mapLine(split[0])
        val output = mapLine(split[1])

        return EnhancementRule(input, output)
    }

    private fun mapLine(line: String): Array<Array<Boolean>> {
        return line
                .split("/")
                .map {
                    it.map {
                        when (it) {
                            '#' -> true
                            '.' -> false
                            else -> throw IOException("Bad character: $it")
                        }
                    }.toTypedArray() }
                .toTypedArray()
    }

}

