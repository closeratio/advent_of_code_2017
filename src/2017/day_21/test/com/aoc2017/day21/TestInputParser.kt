package com.aoc2017.day21

import org.testng.Assert
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class TestInputParser {

    @Test
    fun testParseRule1() {
        val rule = InputParser.parseRule("../.# => ##./#../...")

        assertEquals(rule.input, arrayOf(
                arrayOf(false, false),
                arrayOf(false, true)
        ))

        assertEquals(rule.output, arrayOf(
                arrayOf(true, true, false),
                arrayOf(true, false, false),
                arrayOf(false, false, false)
        ))
    }

    @Test
    fun testParseRule2() {
        val rule = InputParser.parseRule(".#./..#/### => #..#/..../..../#..#")

        assertEquals(rule.input, arrayOf(
                arrayOf(false, true, false),
                arrayOf(false, false, true),
                arrayOf(true, true, true)
        ))

        assertEquals(rule.output, arrayOf(
                arrayOf(true, false, false, true),
                arrayOf(false, false, false, false),
                arrayOf(false, false, false, false),
                arrayOf(true, false, false, true)
        ))
    }

    @Test
    fun testParseInputState() {
        val state = InputParser.parseState(".#.\n..#\n###")

        assertEquals(state, arrayOf(
                arrayOf(false, true, false),
                arrayOf(false, false, true),
                arrayOf(true, true, true)))
    }

}