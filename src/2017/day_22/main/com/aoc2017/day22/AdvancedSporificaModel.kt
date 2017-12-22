package com.aoc2017.day22

import com.aoc2017.day22.InfectionState.*
import java.io.IOException

class AdvancedSporificaModel private constructor(
        val positionMap: MutableMap<Vec2, InfectionState>
) {

    var currPos = Vec2.ZERO
    var currDir = Direction.UP

    var infectionCount = 0

    fun iterate(times: Int = 1) {

        repeat(times) {
            updateDirection()
            updateInfectionState()
            currPos = currPos.getAdjacent(currDir)
        }
    }

    private fun updateDirection() {
        currDir = when (positionMap.getOrPut(currPos, { CLEAN })) {
            CLEAN -> currDir.left()
            WEAKENED -> currDir
            INFECTED -> currDir.right()
            FLAGGED -> currDir.opposite()
            else -> throw RuntimeException("Unhandled map state")
        }
    }

    private fun updateInfectionState() {
        positionMap[currPos] = when (positionMap[currPos]) {
            CLEAN -> WEAKENED
            WEAKENED -> {
                infectionCount++
                INFECTED
            }
            INFECTED -> FLAGGED
            FLAGGED -> CLEAN
            else -> throw RuntimeException("Unhandled map state")
        }
    }

    companion object {
        fun create(mapString: String): AdvancedSporificaModel {
            val lines = mapString.trim().split("\n")

            val size = lines.size
            var y = size / 2

            val map = HashMap<Vec2, InfectionState>()

            lines.forEach { line ->
                var x = -size / 2
                line.forEach {
                    map[Vec2(x, y)] = when(it) {
                        '#' -> INFECTED
                        '.' -> CLEAN
                        else -> throw IOException("Bad map character: $it")
                    }
                    x++
                }
                y--
            }

            return AdvancedSporificaModel(map)
        }
    }

}

