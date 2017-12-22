package com.aoc2017.day21

import java.io.IOException

object InputParser {

    fun parseState(data: String): PixelState {
        return mapLine(data.trim().replace("\n", "/"))
    }

    fun parseRule(line: String): HashMap<PixelState, PixelState> {
        val split = line.split("=>").map { it.trim() }
        val inputs = (mapLine(split[0]) as PixelState).getPermutations()
        val output = mapLine(split[1])

        val mapping = hashMapOf<PixelState, PixelState>()
        inputs.forEach {
            mapping[it] = output
        }

        return mapping
    }

    private fun mapLine(line: String): PixelState {
        return PixelState(line
                .split("/")
                .map {
                    it.map {
                        when (it) {
                            '#' -> true
                            '.' -> false
                            else -> throw IOException("Bad character: $it")
                        }
                    }.toTypedArray() }
                .toTypedArray())
    }

}

