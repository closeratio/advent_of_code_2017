package com.aoc2017.day17

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class TestSpinLock {

    @Test
    fun testInsert() {
        val sl = SpinLock(3)

        sl.insertNext()
        assertEquals(sl.values, listOf(0, 1))
        assertEquals(sl.currPos, 1)

        sl.insertNext()
        assertEquals(sl.values, listOf(0, 2, 1))
        assertEquals(sl.currPos, 1)

        sl.insertNext()
        assertEquals(sl.values, listOf(0, 2, 3, 1))
        assertEquals(sl.currPos, 2)

        sl.insertNext()
        assertEquals(sl.values, listOf(0, 2, 4, 3, 1))
        assertEquals(sl.currPos, 2)

        sl.insertNext()
        assertEquals(sl.values, listOf(0, 5, 2, 4, 3, 1))
        assertEquals(sl.currPos, 1)

        sl.insertNext()
        assertEquals(sl.values, listOf(0, 5, 2, 4, 3, 6, 1))
        assertEquals(sl.currPos, 5)
    }

    @Test
    fun testValueAfterLast() {
        val sl = SpinLock(3)
        repeat(2017) {
            sl.insertNext()
        }

        assertEquals(sl.values[sl.currPos + 1], 638)
    }

    @Test
    fun testActualValueAfterLast() {
        val sl = SpinLock(316)
        repeat(2017) {
            sl.insertNext()
        }

        println(sl.values[sl.currPos + 1])
    }

    @Test
    fun testActualLargeValueAfterLast() {
        var currSize = 1
        var currPos = 0
        var answer = 0
        val steps = 316

        repeat(50_000_000) {
            val insValue = it + 1
            val nextPos = ((currPos + steps) % currSize) + 1

            if (nextPos == 1) {
                answer = insValue
            }

            currPos = nextPos
            currSize++
        }

        println(answer)
    }

}