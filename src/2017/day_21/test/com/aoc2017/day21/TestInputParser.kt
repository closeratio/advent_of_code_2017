package com.aoc2017.day21

import com.aoc2017.day21.InputParser.parseRule
import com.aoc2017.day21.InputParser.parseState
import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import org.testng.annotations.Test

class TestInputParser {

    val TEST_RULESET = arrayOf(
            "../.# => ##./#../...",
            ".#./..#/### => #..#/..../..../#..#"
    )

    val DEFAULT_STATE = ".#.\n..#\n###"

    @Test
    fun testParseRule1() {
        val rules = parseRule(TEST_RULESET[0])

        assertTrue(rules.keys.contains(PixelState(arrayOf(
                arrayOf(false, false),
                arrayOf(false, true)
        ))))

        assertTrue(rules.values.contains(PixelState(arrayOf(
                arrayOf(true, true, false),
                arrayOf(true, false, false),
                arrayOf(false, false, false)
        ))))
    }

    @Test
    fun testParseRule2() {
        val rules = parseRule(TEST_RULESET[1])

        assertTrue(rules.keys.contains(PixelState(arrayOf(
                arrayOf(false, true, false),
                arrayOf(false, false, true),
                arrayOf(true, true, true)
        ))))

        assertTrue(rules.values.contains(PixelState(arrayOf(
                arrayOf(true, false, false, true),
                arrayOf(false, false, false, false),
                arrayOf(false, false, false, false),
                arrayOf(true, false, false, true)
        ))))
    }

    @Test
    fun testParseInputState() {
        val state = parseState(DEFAULT_STATE)

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

        assertEquals((PixelState(
                arrayOf(
                        arrayOf(false, false, true),
                        arrayOf(true, true, false),
                        arrayOf(false, true, false)))).rotateState(),
                PixelState(arrayOf(
                        arrayOf(false, true, false),
                        arrayOf(true, true, false),
                        arrayOf(false, false, true))))
    }

    @Test
    fun testStateFlip() {
        assertEquals((PixelState(
                arrayOf(
                        arrayOf(false, true),
                        arrayOf(true, false)))).flipState(),
                PixelState(arrayOf(
                        arrayOf(true, false),
                        arrayOf(false, true))))

        assertEquals((PixelState(
                arrayOf(
                        arrayOf(false, true, false),
                        arrayOf(true, false, false),
                        arrayOf(false, true, true)))).flipState(),
                PixelState(arrayOf(
                        arrayOf(false, true, false),
                        arrayOf(false, false, true),
                        arrayOf(true, true, false))))
    }

    @Test
    fun testStatePermutations1() {
        val perms = (PixelState(arrayOf(
                arrayOf(false, true),
                arrayOf(true, false)))).getPermutations()

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
        val perms = (PixelState(arrayOf(
                arrayOf(true, false),
                arrayOf(true, false)))).getPermutations()

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
        val perms = (PixelState(arrayOf(
                arrayOf(false, false, false),
                arrayOf(false, true, false),
                arrayOf(false, false, false)))).getPermutations()

        assertEquals(perms.size, 1)

        assertEquals(perms[0], PixelState(arrayOf(
                arrayOf(false, false, false),
                arrayOf(false, true, false),
                arrayOf(false, false, false)
        )))
    }

    @Test
    fun testStatePermutations4() {
        val perms = (PixelState(arrayOf(
                arrayOf(true, false, false),
                arrayOf(true, true, false),
                arrayOf(false, false, false))) as PixelState).getPermutations()

        assertEquals(perms.size, 8)
    }

    @Test
    fun testIterate() {
        val ruleset = parseRule(TEST_RULESET[0]) +  parseRule(TEST_RULESET[1])

        val state = parseState(DEFAULT_STATE)

        val iter1State = state.iterate(ruleset)
        assertEquals(iter1State, PixelState(arrayOf(
                arrayOf(true, false, false, true),
                arrayOf(false, false, false, false),
                arrayOf(false, false, false, false),
                arrayOf(true, false, false, true)
        )))

        val iter2State = iter1State.iterate(ruleset)

        assertEquals(iter2State, PixelState(arrayOf(
                arrayOf(true, true, false, true, true, false),
                arrayOf(true, false, false, true, false, false),
                arrayOf(false, false, false, false, false, false),
                arrayOf(true, true, false, true, true, false),
                arrayOf(true, false, false, true, false, false),
                arrayOf(false, false, false, false, false, false))))

    }

    @Test
    fun testCountOnPixels() {
        val ruleset = parseRule(TEST_RULESET[0]) + parseRule(TEST_RULESET[1])

        val state = parseState(DEFAULT_STATE)

        val finalState = state
                .iterate(ruleset)
                .iterate(ruleset)

        assertEquals(finalState.getOnPixelCount(), 12)
    }

    @Test
    fun testActualOnPixels() {
        val ruleSets = javaClass.getResource("/input.txt")
                .readText()
                .trim()
                .split("\n")
                .map { parseRule(it) }

        val rules = HashMap<PixelState, PixelState>()
        ruleSets.forEach { ruleSet ->
            ruleSet.forEach { k, v -> rules[k] = v }
        }

        val state = parseState(DEFAULT_STATE)

        val finalState = state
                .iterate(rules)
                .iterate(rules)
                .iterate(rules)
                .iterate(rules)
                .iterate(rules)

        println(finalState.getOnPixelCount())
    }

    @Test
    fun testActualOnPixelsMoreIters() {
        val ruleSets = javaClass.getResource("/input.txt")
                .readText()
                .trim()
                .split("\n")
                .map { parseRule(it) }

        val rules = HashMap<PixelState, PixelState>()
        ruleSets.forEach { ruleSet ->
            ruleSet.forEach { k, v -> rules[k] = v }
        }

        var state = parseState(DEFAULT_STATE)

        repeat(18) {
            state = state.iterate(rules)
        }

        println(state.getOnPixelCount())
    }

}