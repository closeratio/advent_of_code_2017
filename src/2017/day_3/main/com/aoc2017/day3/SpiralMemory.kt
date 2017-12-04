package com.aoc2017.day3

object SpiralMemory {

    // Bleh... non-thread safe global state, we can refactor later if needed
    private val memLocCache = HashMap<Int, Vec2>()
    private val indexCache = HashMap<Vec2, Int>()
    private val valueCache = HashMap<Int, Int>()

    fun GetMemoryLocation(index: Int): Vec2 {
        if (memLocCache[index] != null) return memLocCache[index]!!

        var currPos = Vec2()
        var ind = 0

        var extents = Extents()

        while (ind < index - 1) {
            if (extents.right == Math.abs(extents.down)) {
                currPos = currPos.right()
            } else if (extents.up < extents.right) {
                currPos = currPos.up()
            } else if (Math.abs(extents.left) < extents.up) {
                currPos = currPos.left()
            } else {
                currPos = currPos.down()
            }

            extents = extents.extend(currPos)
            ind++
        }

        memLocCache[index] = currPos

        return currPos
    }

    fun GetIndex(reqPos: Vec2): Int {
        if (indexCache[reqPos] != null) return indexCache[reqPos]!!

        var index = 1
        var pos = GetMemoryLocation(index)

        while (pos != reqPos) {
            index++
            pos = GetMemoryLocation(index)
        }

        indexCache[reqPos] = index

        return index
    }

    fun GetCellValue(index: Int): Int {
        if (index == 1) return 1
        if (valueCache[index] != null) return valueCache[index]!!

        val value = GetMemoryLocation(index).adjacentPositions()
                .filter { GetIndex(it) < index }
                .map { GetCellValue(GetIndex(it)) }
                .sum()

        valueCache[index] = value

        return value
    }


}

