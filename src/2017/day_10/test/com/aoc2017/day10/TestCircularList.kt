package com.aoc2017.day10

import org.testng.Assert.assertEquals
import org.testng.annotations.Test
import java.io.File

class TestCircularList {

    @Test
    fun testSelect1() {
        val cl = CircularList(5)
        assertEquals(cl.selectElements(3), listOf(0, 1, 2))
    }

    @Test
    fun testSelect2() {
        val cl = CircularList(4)
        assertEquals(cl.selectElements(2), listOf(0, 1))
    }

    @Test
    fun testSelect3() {
        val cl = CircularList(10)
        cl.currPosition = 8
        assertEquals(cl.selectElements(5), listOf(8, 9, 0, 1, 2))
    }

    @Test
    fun testReverse1() {
        val cl = CircularList(7)
        cl.reverse(4)
        assertEquals(cl.list, listOf(3, 2, 1, 0, 4, 5, 6))
        assertEquals(cl.currPosition, 4)
        assertEquals(cl.skipSize, 1)
    }

    @Test
    fun testHashWithTestData() {
        val cl = CircularList(5)

        cl.reverse(3)
        assertEquals(cl.list, listOf(2, 1, 0, 3, 4))
        assertEquals(cl.currPosition, 3)
        assertEquals(cl.skipSize, 1)

        cl.reverse(4)
        assertEquals(cl.list, listOf(4, 3, 0, 1, 2))
        assertEquals(cl.currPosition, 3)
        assertEquals(cl.skipSize, 2)

        cl.reverse(1)
        assertEquals(cl.list, listOf(4, 3, 0, 1, 2))
        assertEquals(cl.currPosition, 1)
        assertEquals(cl.skipSize, 3)

        cl.reverse(5)
        assertEquals(cl.list, listOf(3, 4, 2, 1, 0))
        assertEquals(cl.currPosition, 4)
        assertEquals(cl.skipSize, 4)

        assertEquals(cl.getSimpleHash(), 12)
    }

    @Test
    fun testHasWithActualInput() {
        val cl = CircularList(256)

        val lengths = File(javaClass.getResource("/input.txt").toURI())
                .readText()
                .trim()
                .split(",")
                .map { it.toInt() }

        lengths.forEach { cl.reverse(it) }

        println(cl.getSimpleHash())
    }

    @Test
    fun testParseASCIIStrings() {
        val lengths = CircularList.getLengthsFromString("1,2,3")
        assertEquals(lengths, listOf(49,44,50,44,51))
    }

    @Test
    fun testGenerateLengths() {
        val lengths = CircularList.generateLengths("1,2,3")
        assertEquals(lengths, listOf(49,44,50,44,51,17,31,73,47,23))
    }

    @Test
    fun testKnotHash1() {
        val cl = CircularList(256)
        assertEquals(cl.getKnotHash(""), "a2582a3a0e66e6e86e3812dcb672a272")
    }

    @Test
    fun testKnotHash2() {
        val cl = CircularList(256)
        assertEquals(cl.getKnotHash("AoC 2017"), "33efeb34ea91902bb2f59c9920caa6cd")
    }

    @Test
    fun testKnotHash3() {
        val cl = CircularList(256)
        assertEquals(cl.getKnotHash("1,2,3"), "3efbe78a8d82f29979031a4aa0b16a9d")
    }

    @Test
    fun testKnotHash4() {
        val cl = CircularList(256)
        assertEquals(cl.getKnotHash("1,2,4"), "63960835bcdc130f0b66d7ff4f6a5a8e")
    }

    @Test
    fun testActualKnotHash() {
        val cl = CircularList(256)
        println(cl.getKnotHash(
                File(javaClass.getResource("/input.txt").toURI()).readText().trim()))
    }



}