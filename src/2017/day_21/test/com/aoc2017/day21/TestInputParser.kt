package com.aoc2017.day21

import com.aoc2017.day21.InputParser.parseRule
import com.aoc2017.day21.InputParser.parseState
import org.testng.Assert
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

        assertTrue(rules.keys.contains(PixelStateNode.create(arrayOf(
                arrayOf(false, false),
                arrayOf(false, true)
        ))))

        assertTrue(rules.values.contains(PixelStateNode.create(arrayOf(
                arrayOf(true, true, false),
                arrayOf(true, false, false),
                arrayOf(false, false, false)
        ))))
    }

    @Test
    fun testParseRule2() {
        val rules = parseRule(TEST_RULESET[1])

        assertTrue(rules.keys.contains(PixelStateNode.create(arrayOf(
                arrayOf(false, true, false),
                arrayOf(false, false, true),
                arrayOf(true, true, true)
        ))))

        assertTrue(rules.values.contains(PixelStateNode.create(arrayOf(
                arrayOf(true, false, false, true),
                arrayOf(false, false, false, false),
                arrayOf(false, false, false, false),
                arrayOf(true, false, false, true)
        ))))
    }

    @Test
    fun testParseInputState() {
        val state = parseState(DEFAULT_STATE)

        assertEquals(state, PixelStateNode.create(arrayOf(
                arrayOf(false, true, false),
                arrayOf(false, false, true),
                arrayOf(true, true, true))))
    }

    @Test
    fun testStateRotation() {
        assertEquals((PixelStateNode.create(
                arrayOf(
                        arrayOf(false, false),
                        arrayOf(true, true))) as PixelStateNode).rotateState(),
                PixelStateNode.create(arrayOf(
                        arrayOf(true, false),
                        arrayOf(true, false))))

        assertEquals((PixelStateNode.create(
                arrayOf(
                        arrayOf(false, false, true),
                        arrayOf(true, true, false),
                        arrayOf(false, true, false))) as PixelStateNode).rotateState(),
                PixelStateNode.create(arrayOf(
                        arrayOf(false, true, false),
                        arrayOf(true, true, false),
                        arrayOf(false, false, true))))
    }

    @Test
    fun testStateFlip() {
        assertEquals((PixelStateNode.create(
                arrayOf(
                        arrayOf(false, true),
                        arrayOf(true, false))) as PixelStateNode).flipState(),
                PixelStateNode.create(arrayOf(
                        arrayOf(true, false),
                        arrayOf(false, true))))

        assertEquals((PixelStateNode.create(
                arrayOf(
                        arrayOf(false, true, false),
                        arrayOf(true, false, false),
                        arrayOf(false, true, true))) as PixelStateNode).flipState(),
                PixelStateNode.create(arrayOf(
                        arrayOf(false, true, false),
                        arrayOf(false, false, true),
                        arrayOf(true, true, false))))
    }

    @Test
    fun testStatePermutations1() {
        val perms = (PixelStateNode.create(arrayOf(
                arrayOf(false, true),
                arrayOf(true, false))) as PixelStateNode).getPermutations()

        assertEquals(perms.size, 2)

        assertEquals(perms[0], PixelStateNode.create(arrayOf(
                arrayOf(false, true),
                arrayOf(true, false)
        )))

        assertEquals(perms[1], PixelStateNode.create(arrayOf(
                arrayOf(true, false),
                arrayOf(false, true)
        )))
    }

    @Test
    fun testStatePermutations2() {
        val perms = (PixelStateNode.create(arrayOf(
                arrayOf(true, false),
                arrayOf(true, false))) as PixelStateNode).getPermutations()

        assertEquals(perms.size, 4)

        assertEquals(perms[0], PixelStateNode.create(arrayOf(
                arrayOf(true, false),
                arrayOf(true, false)
        )))

        assertEquals(perms[1], PixelStateNode.create(arrayOf(
                arrayOf(false, true),
                arrayOf(false, true)
        )))

        assertEquals(perms[2], PixelStateNode.create(arrayOf(
                arrayOf(true, true),
                arrayOf(false, false)
        )))

        assertEquals(perms[3], PixelStateNode.create(arrayOf(
                arrayOf(false, false),
                arrayOf(true, true)
        )))
    }

    @Test
    fun testStatePermutations3() {
        val perms = (PixelStateNode.create(arrayOf(
                arrayOf(false, false, false),
                arrayOf(false, true, false),
                arrayOf(false, false, false))) as PixelStateNode).getPermutations()

        assertEquals(perms.size, 1)

        assertEquals(perms[0], PixelStateNode.create(arrayOf(
                arrayOf(false, false, false),
                arrayOf(false, true, false),
                arrayOf(false, false, false)
        )))
    }

    @Test
    fun testStatePermutations4() {
        val perms = (PixelStateNode.create(arrayOf(
                arrayOf(true, false, false),
                arrayOf(true, true, false),
                arrayOf(false, false, false))) as PixelStateNode).getPermutations()

        assertEquals(perms.size, 8)
    }

    @Test
    fun testApplyRuleset() {
        val ruleset = parseRule(TEST_RULESET[0]) +  parseRule(TEST_RULESET[1])

        val state = parseState(DEFAULT_STATE)

        val iter1State = state.applyRuleset(ruleset)
        assertEquals(iter1State, PixelStateNode.create(arrayOf(
                arrayOf(true, false, false, true),
                arrayOf(false, false, false, false),
                arrayOf(false, false, false, false),
                arrayOf(true, false, false, true)
        )))

        val iter2State = iter1State.applyRuleset(ruleset)

        val tempPSN = PixelStateNode.create(arrayOf(
                arrayOf(true, true, false),
                arrayOf(true, false, false),
                arrayOf(false, false, false)
        ))

        assertEquals(iter2State, PixelStateGroup.create(arrayOf(tempPSN, tempPSN, tempPSN, tempPSN)))

    }

    @Test
    fun testCountOnPixels() {
        val ruleset = parseRule(TEST_RULESET[0]) + parseRule(TEST_RULESET[1])

        val state = parseState(DEFAULT_STATE)

        val finalState = state
                .applyRuleset(ruleset)
                .applyRuleset(ruleset)

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
                .applyRuleset(rules)
                .applyRuleset(rules)
                .applyRuleset(rules)
                .applyRuleset(rules)
                .applyRuleset(rules)

        println("On count: ${finalState.getOnPixelCount()}")
        println("Node count: ${finalState.getNodeCount()}")
    }

}