package com.aoc2017.day12

import org.testng.Assert.assertEquals
import org.testng.annotations.Test
import java.io.File

class TestPipeProcessor {

    private val TEST_LINES = arrayOf(
            "0 <-> 2",
            "1 <-> 1",
            "2 <-> 0, 3, 4",
            "3 <-> 2, 4",
            "4 <-> 2, 3, 6",
            "5 <-> 6",
            "6 <-> 4, 5")

    @Test
    fun testParseLines() {
        val pn = ProgramNetwork()

        pn.parseLine(TEST_LINES[0])
        assertEquals(pn.programs.size, 2)
        assertEquals(pn.programs[0]!!.connections, setOf(Program(2)))
        assertEquals(pn.programs[2]!!.connections, setOf(Program(0)))

        pn.parseLine(TEST_LINES[1])
        assertEquals(pn.programs.size, 3)
        assertEquals(pn.programs[1]!!.connections, setOf<Program>())

        pn.parseLine(TEST_LINES[2])
        assertEquals(pn.programs.size, 5)
        assertEquals(pn.programs[2]!!.connections, setOf(Program(0), Program(3), Program(4)))
        assertEquals(pn.programs[3]!!.connections, setOf(Program(2)))
        assertEquals(pn.programs[4]!!.connections, setOf(Program(2)))
    }

    @Test
    fun testConnectedPrograms1() {
        val pn = ProgramNetwork()

        pn.parseLine(TEST_LINES[0])
        assertEquals(pn.findConnectedPrograms(0), setOf(Program(0), Program(2)))

        pn.parseLine(TEST_LINES[1])
        assertEquals(pn.findConnectedPrograms(0), setOf(Program(0), Program(2)))

        pn.parseLine(TEST_LINES[2])
        assertEquals(pn.findConnectedPrograms(0), setOf(Program(0), Program(2), Program(3), Program(4)))
    }

    @Test
    fun testConnectedPrograms2() {
        val pn = ProgramNetwork().apply {
            TEST_LINES.forEach { parseLine(it) }
        }

        assertEquals(pn.findConnectedPrograms(0).size, 6)

    }

    @Test
    fun testActualConnectedPrograms() {
        val pn = ProgramNetwork().apply {
            File(javaClass.getResource("/input.txt").toURI())
                    .readLines()
                    .forEach { parseLine(it) }
        }

        println(pn.findConnectedPrograms(0).size)
    }

    @Test
    fun testFindGroupCount() {
        val pn = ProgramNetwork().apply {
            TEST_LINES.forEach { parseLine(it) }
        }

        assertEquals(pn.findGroupCount(), 2)
    }

    @Test
    fun testActualGroupCount() {
        val pn = ProgramNetwork().apply {
            File(javaClass.getResource("/input.txt").toURI())
                    .readLines()
                    .forEach { parseLine(it) }
        }

        println(pn.findGroupCount())
    }
}

