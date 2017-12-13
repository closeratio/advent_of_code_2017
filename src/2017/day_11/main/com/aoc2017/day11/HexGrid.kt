package com.aoc2017.day11

import com.aoc2017.day11.Direction.*
import java.io.IOException

class HexGrid {

    fun getStepCount(
            end: HexCell,
            openMap: HashMap<HexCell, Int> = hashMapOf(HexCell() to 0),
            closedMap: HashMap<HexCell, Int> = HashMap()
    ): Int {

        if (end in openMap) {
            return openMap[end]!!
        } else if (end in closedMap) {
            return closedMap[end]!!
        }

        // Keep iterating until the set of cell contains the end cell
        while (!openMap.contains(end)) {

            HashMap(openMap).forEach { cell, iterCount ->
                val cells = Direction.values().map { cell.adjacent(it) }

                cells.forEach {
                    if (it !in openMap && it !in closedMap) {
                        openMap.put(it, iterCount + 1)
                    }
                }

                openMap.remove(cell)
                closedMap.put(cell, iterCount)
            }
        }

        return openMap[end]!!
    }

    fun getStepCount(steps: String): Int {
        return getStepCount(getLastCell(steps))
    }

    fun getMaxStepCount(steps: String): Int {
        val directions = parseDirections(steps)
        val cells = mutableListOf(HexCell())
        val tested = HashSet<HexCell>()

        directions.forEach {
            val adj = cells.last().adjacent(it)
            cells.add(adj)

            if (adj !in tested) {
                tested.add(adj)
            }
        }

        val openMap = hashMapOf(HexCell() to 0)
        val closedMap = HashMap<HexCell, Int>()

        tested.forEach { getStepCount(it, openMap, closedMap) }

        return (openMap + closedMap).values.max()!!
    }

    private fun getLastCell(steps: String): HexCell {
        var curr = HexCell()
        parseDirections(steps).forEach {
            curr = curr.adjacent(it)
        }
        return curr
    }

    companion object {
        fun parseDirections(dirs: String): List<Direction> {
            return dirs.trim()
                    .split(",")
                    .map {
                        when (it.trim()) {
                            "n" -> NORTH
                            "ne" -> NORTH_EAST
                            "se" -> SOUTH_EAST
                            "s" -> SOUTH
                            "sw" -> SOUTH_WEST
                            "nw" -> NORTH_WEST
                            else -> throw IOException("Bad direction: $it")
                        }
                    }
        }
    }

}

