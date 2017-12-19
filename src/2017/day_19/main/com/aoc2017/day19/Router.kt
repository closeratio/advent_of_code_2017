package com.aoc2017.day19

import com.aoc2017.day19.Direction.*

class Router(
        val inputString: String
) {

    val grid = assembleGrid()
    val charList = ArrayList<String>()

    var currentPosition = getInitialPosition()
    var direction = DOWN
    var finished = false

    var iterCount = 1


    fun assembleGrid(): Map<Vec2, String> {
        val gridMap = HashMap<Vec2, String>()

        val lines = inputString.trim('\n').split("\n")
        val lineCount = lines.size

        lines.forEachIndexed { y, line ->
            val yCoord = lineCount - y - 1

            line.forEachIndexed { x, value ->
                gridMap.put(Vec2(x, yCoord), value.toString())
            }
        }

        return gridMap
    }

    fun getInitialPosition(): Vec2 {
        val gridHeight = getGridHeight() - 1
        return grid
                .filter { it.key.y == gridHeight }
                .filter { it.value == "|" }
                .map { it.key }
                .sortedBy { it.x }
                .first()
    }

    fun getGridWidth(): Int {
        val xs = grid
                .map { it.key }
                .filter { it.y == 0 }
                .map { it.x }
        return xs.max()!! - xs.min()!! + 1
    }

    fun getGridHeight(): Int {
        val ys = grid
                .map { it.key }
                .filter { it.x == 0 }
                .map { it.y }
        return ys.max()!! - ys.min()!! + 1
    }

    fun traverse() {
        while (!finished) {
            iterate()
        }
    }

    fun iterate() {
        move()
        updateDirection()
        iterCount++
    }

    fun move() {
        currentPosition = currentPosition.getAdjacent(direction)
    }

    fun updateDirection() {
        val currChar = grid[currentPosition]

        if (currChar == null) {
            throw RuntimeException("Fallen off the edge of the grid")
        } else {
            when (currChar) {
                "+" -> {
                    direction = if (direction == DOWN || direction == UP) {
                        when {
                            grid[currentPosition.getAdjacent(LEFT)] != " " -> LEFT
                            grid[currentPosition.getAdjacent(RIGHT)] != " " -> RIGHT
                            else -> throw RuntimeException("Can't find next step at junction $currentPosition")
                        }
                    } else {
                        when {
                            grid[currentPosition.getAdjacent(UP)] != " " -> UP
                            grid[currentPosition.getAdjacent(DOWN)] != " " -> DOWN
                            else -> throw RuntimeException("Can't find next step at junction $currentPosition")
                        }
                    }
                }
                " " -> throw RuntimeException("Fallen off the path somehow")
                "|", "-" -> { /* Don't do anything for path characters */ }
                else -> {
                    charList.add(currChar)

                    // Check for finish
                    val nextChar = grid[currentPosition.getAdjacent(direction)]
                    if (nextChar == null || nextChar == " ") {
                        finished = true
                    }
                }
            }
        }
    }

}

