package com.aoc2017.day3

import org.testng.Assert
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class SpiralMemoryTest {

    @Test
    fun testSquareLocation() {
        assertEquals(SpiralMemory.GetMemoryLocation(1), Vec2())
        assertEquals(SpiralMemory.GetMemoryLocation(2), Vec2(1, 0))
        assertEquals(SpiralMemory.GetMemoryLocation(3), Vec2(1, 1))

        assertEquals(SpiralMemory.GetMemoryLocation(5), Vec2(-1, 1))
        assertEquals(SpiralMemory.GetMemoryLocation(7), Vec2(-1, -1))

        assertEquals(SpiralMemory.GetMemoryLocation(9), Vec2(1, -1))
        assertEquals(SpiralMemory.GetMemoryLocation(10), Vec2(2, -1))

        assertEquals(SpiralMemory.GetMemoryLocation(22), Vec2(-1, -2))
    }

    @Test
    fun testManhattanDistance() {
        assertEquals(SpiralMemory.GetMemoryLocation(1).manDistance(), 0)

        assertEquals(SpiralMemory.GetMemoryLocation(12).manDistance(), 3)

        assertEquals(SpiralMemory.GetMemoryLocation(23).manDistance(), 2)

        assertEquals(SpiralMemory.GetMemoryLocation(1024).manDistance(), 31)
    }

    @Test
    fun testActualDistance() {
        println(SpiralMemory.GetMemoryLocation(289326).manDistance())
    }

    @Test
    fun testIndex() {
        assertEquals(SpiralMemory.GetIndex(Vec2()), 1)

        assertEquals(SpiralMemory.GetIndex(Vec2(1, 0)), 2)

        assertEquals(SpiralMemory.GetIndex(Vec2(-2, -2)), 21)
    }

    @Test
    fun testCellValue() {
        assertEquals(SpiralMemory.GetCellValue(1), 1)
        assertEquals(SpiralMemory.GetCellValue(2), 1)

        assertEquals(SpiralMemory.GetCellValue(3), 2)
        assertEquals(SpiralMemory.GetCellValue(4), 4)

        assertEquals(SpiralMemory.GetCellValue(21), 362)
        assertEquals(SpiralMemory.GetCellValue(22), 747)
    }

    @Test
    fun testActualCellValue() {
        val limit = 289326

        var index = 1
        var curr = SpiralMemory.GetCellValue(index)
        while (curr <= limit) {
            index++
            curr = SpiralMemory.GetCellValue(index)
        }

        println(curr)
    }


}