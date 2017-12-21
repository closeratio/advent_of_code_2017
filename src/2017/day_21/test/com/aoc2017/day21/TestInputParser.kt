package com.aoc2017.day21

import jdk.internal.util.xml.impl.Input
import org.testng.Assert
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class TestInputParser {

    val TEST_RULESET = arrayOf(
            "../.# => ##./#../...",
            ".#./..#/### => #..#/..../..../#..#"
    )

    val DEFAULT_STATE = ".#.\n..#\n###"

    @Test
    fun testParseRule1() {
        val rule = InputParser.parseRule(TEST_RULESET[0])

        assertEquals(rule.input, PixelState(arrayOf(
                arrayOf(false, false),
                arrayOf(false, true)
        )))

        assertEquals(rule.output, PixelState(arrayOf(
                arrayOf(true, true, false),
                arrayOf(true, false, false),
                arrayOf(false, false, false)
        )))
    }

    @Test
    fun testParseRule2() {
        val rule = InputParser.parseRule(TEST_RULESET[1])

        assertEquals(rule.input, PixelState(arrayOf(
                arrayOf(false, true, false),
                arrayOf(false, false, true),
                arrayOf(true, true, true)
        )))

        assertEquals(rule.output, PixelState(arrayOf(
                arrayOf(true, false, false, true),
                arrayOf(false, false, false, false),
                arrayOf(false, false, false, false),
                arrayOf(true, false, false, true)
        )))
    }

    @Test
    fun testParseInputState() {
        val state = InputParser.parseState(DEFAULT_STATE)

        assertEquals(state, PixelState(arrayOf(
                arrayOf(false, true, false),
                arrayOf(false, false, true),
                arrayOf(true, true, true))))
    }

    @Test
    fun testStateRotation() {
        assertEquals(PixelState(
                arrayOf(
                        arrayOf(false, false),
                        arrayOf(true, true))).rotateState(),
                PixelState(arrayOf(
                        arrayOf(true, false),
                        arrayOf(true, false))))

        assertEquals(PixelState(
                arrayOf(
                        arrayOf(false, false, true),
                        arrayOf(true, true, false),
                        arrayOf(false, true, false))).rotateState(),
                PixelState(arrayOf(
                        arrayOf(false, true, false),
                        arrayOf(true, true, false),
                        arrayOf(false, false, true))))
    }

    @Test
    fun testStateFlip() {
        assertEquals(PixelState(
                arrayOf(
                        arrayOf(false, true),
                        arrayOf(true, false))).flipState(),
                PixelState(arrayOf(
                        arrayOf(true, false),
                        arrayOf(false, true))))

        assertEquals(PixelState(
                arrayOf(
                        arrayOf(false, true, false),
                        arrayOf(true, false, false),
                        arrayOf(false, true, true))).flipState(),
                PixelState(arrayOf(
                        arrayOf(false, true, false),
                        arrayOf(false, false, true),
                        arrayOf(true, true, false))))
    }

    @Test
    fun testStatePermutations1() {
        val perms = PixelState(arrayOf(
                arrayOf(false, true),
                arrayOf(true, false))).getPermutations()

        assertEquals(perms.size, 2)

        assertEquals(perms[0], PixelState(arrayOf(
                arrayOf(false, true),
                arrayOf(true, false)
        )))

        assertEquals(perms[1], PixelState(arrayOf(
                arrayOf(true, false),
                arrayOf(false, true)
        )))
    }

    @Test
    fun testStatePermutations2() {
        val perms = PixelState(arrayOf(
                arrayOf(true, false),
                arrayOf(true, false))).getPermutations()

        assertEquals(perms.size, 4)

        assertEquals(perms[0], PixelState(arrayOf(
                arrayOf(true, false),
                arrayOf(true, false)
        )))

        assertEquals(perms[1], PixelState(arrayOf(
                arrayOf(false, true),
                arrayOf(false, true)
        )))

        assertEquals(perms[2], PixelState(arrayOf(
                arrayOf(true, true),
                arrayOf(false, false)
        )))

        assertEquals(perms[3], PixelState(arrayOf(
                arrayOf(false, false),
                arrayOf(true, true)
        )))
    }

    @Test
    fun testStatePermutations3() {
        val perms = PixelState(arrayOf(
                arrayOf(false, false, false),
                arrayOf(false, true, false),
                arrayOf(false, false, false))).getPermutations()

        assertEquals(perms.size, 1)

        assertEquals(perms[0], PixelState(arrayOf(
                arrayOf(false, false, false),
                arrayOf(false, true, false),
                arrayOf(false, false, false)
        )))
    }

    @Test
    fun testStatePermutations4() {
        val perms = PixelState(arrayOf(
                arrayOf(true, false, false),
                arrayOf(true, true, false),
                arrayOf(false, false, false))).getPermutations()

        assertEquals(perms.size, 8)
    }

    @Test
    fun testApplyRuleset() {
        val ruleset = listOf(
                InputParser.parseRule(TEST_RULESET[0]),
                InputParser.parseRule(TEST_RULESET[1])
        )

        val state = InputParser.parseState(DEFAULT_STATE)

        val iter1State = state.applyRuleset(ruleset)
        Assert.assertEquals(iter1State, PixelState(arrayOf(
                arrayOf(true, false, false, true),
                arrayOf(false, false, false, false),
                arrayOf(false, false, false, false),
                arrayOf(true, false, false, true)
        )))

    }

}