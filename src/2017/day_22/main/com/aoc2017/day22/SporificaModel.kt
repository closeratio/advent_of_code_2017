package com.aoc2017.day22

import java.io.IOException

class SporificaModel private constructor(
        val positionMap: MutableMap<Vec2, Boolean>
) {

    var currPos = Vec2.ZERO
    var currDir = Direction.UP

    fun iterate(times: Int = 1) {

        repeat(times) {
            currDir = when (positionMap.getOrPut(currPos, { false })) {
                true -> currDir.right()
                false -> currDir.left()
                else -> throw RuntimeException("Unhandled map state")
            }

            positionMap[currPos] = !positionMap[currPos]!!

            currPos = currPos.getAdjacent(currDir)
        }
    }

    companion object {
        fun create(mapString: String): SporificaModel {
            val lines = mapString.trim().split("\n")

            val size = lines.size
            var y = size / 2

            val map = HashMap<Vec2, Boolean>()

            lines.forEach { line ->
                var x = -size / 2
                line.forEach {
                    map[Vec2(x, y)] = when(it) {
                        '#' -> true
                        '.' -> false
                        else -> throw IOException("Bad map character: $it")
                    }
                    x++
                }
                y--
            }

            return SporificaModel(map)
        }
    }

}

