package com.aoc2017.day11

import com.aoc2017.day11.Direction.*
import org.testng.Assert.assertEquals
import org.testng.annotations.Test
import java.io.File

class TestHexGrid {

    @Test
    fun testParseDirections1() {
        val directions = HexGrid.parseDirections("ne,ne,ne")
        assertEquals(directions, listOf(NORTH_EAST, NORTH_EAST, NORTH_EAST))
    }

    @Test
    fun testParseDirections2() {
        val directions = HexGrid.parseDirections("ne,ne,sw,sw")
        assertEquals(directions, listOf(NORTH_EAST, NORTH_EAST, SOUTH_WEST, SOUTH_WEST))
    }

    @Test
    fun testParseDirections3() {
        val directions = HexGrid.parseDirections("ne,ne,s,s")
        assertEquals(directions, listOf(NORTH_EAST, NORTH_EAST, SOUTH, SOUTH))
    }

    @Test
    fun testParseDirections4() {
        val directions = HexGrid.parseDirections("se,sw,se,sw,sw")
        assertEquals(directions, listOf(SOUTH_EAST, SOUTH_WEST, SOUTH_EAST, SOUTH_WEST, SOUTH_WEST))
    }

    @Test
    fun getNextCell() {
        val cell = HexCell()
        val north = cell.adjacent(NORTH)

        assertEquals(north.pos.x, 0)
        assertEquals(north.pos.y, 2)

        val southWest = cell.adjacent(SOUTH_WEST)

        assertEquals(southWest.pos.x, -1)
        assertEquals(southWest.pos.y, -1)
    }

    @Test
    fun testGetStepCount1() {
        assertEquals(HexGrid().getStepCount("ne,ne,ne"), 3)
    }

    @Test
    fun testGetStepCount2() {
        assertEquals(HexGrid().getStepCount("ne,ne,sw,sw"), 0)
    }

    @Test
    fun testGetStepCount3() {
        assertEquals(HexGrid().getStepCount("ne,ne,s,s"), 2)
    }

    @Test
    fun testGetStepCount4() {
        assertEquals(HexGrid().getStepCount("se,sw,se,sw,sw"), 3)
    }

    @Test
    fun testActualStepCount() {
//        println(HexGrid().getStepCount(
//                File(javaClass.getResource("/input.txt").toURI()).readText().trim()))
    }

    @Test
    fun testGetMaxStepCount1() {
        assertEquals(HexGrid().getMaxStepCount("ne,ne,ne"), 3)
    }

    @Test
    fun testGetMaxStepCount2() {
        assertEquals(HexGrid().getMaxStepCount("ne,ne,sw,sw"), 2)
    }

    @Test
    fun testGetMaxStepCount3() {
        assertEquals(HexGrid().getMaxStepCount("ne,ne,s,s"), 2)
    }

    @Test
    fun testGetMaxStepCount4() {
        assertEquals(HexGrid().getMaxStepCount("se,sw,se,sw,sw"), 3)
    }

    @Test
    fun testActualMaxStepCount() {
        println(HexGrid().getMaxStepCount(
                File(javaClass.getResource("/input.txt").toURI()).readText().trim()))
    }

}
