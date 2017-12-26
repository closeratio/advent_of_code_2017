package com.aoc2017.day24

import com.aoc2017.day24.BridgeBuilder.GeneratePermutations
import com.aoc2017.day24.ComponentParser.ParseComponent
import com.aoc2017.day24.ComponentParser.ParseComponents
import org.testng.Assert
import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import org.testng.annotations.Test

class TestBridge {

    private val TEST_DATA =
            "0/2\n" +
            "2/2\n" +
            "2/3\n" +
            "3/4\n" +
            "3/5\n" +
            "0/1\n" +
            "10/1\n" +
            "9/10\n"

    @Test
    fun testParseComponent() {
        val spl = TEST_DATA.trim().split("\n")
        assertEquals(ParseComponent(spl[0]), Component(0, 2))
        assertEquals(ParseComponent(spl[1]), Component(2, 2))
    }

    @Test
    fun testParseComponents() {
        val spl = TEST_DATA.trim().split("\n")
        assertEquals(ParseComponents(
                "${spl[0]}\n${spl[1]}"),
                listOf(Component(0, 2), Component(2, 2)))
    }

    @Test
    fun testGeneratePermutations() {
        val perms = GeneratePermutations(ParseComponents(TEST_DATA))

        assertEquals(perms.size, 11)

        assertTrue(perms.contains(Bridge(listOf(
                Component(0, 1)))))

        assertTrue(perms.contains(Bridge(listOf(
                Component(0, 2), Component(2, 3)))))

        assertTrue(perms.contains(Bridge(listOf(
                Component(0, 2), Component(2, 2), Component(2, 3), Component(3, 4)))))
    }

    @Test
    fun testGetBridgeStrength() {
        val perms = GeneratePermutations(ParseComponents(TEST_DATA))

        assertEquals(
                perms.map { it.getStrength() }.max(),
                31)
    }

    @Test
    fun testGetActualBridgeStrength() {
        val comps = ParseComponents(javaClass.getResource("/input.txt").readText())
        val perms = GeneratePermutations(comps)

        println(perms.map { it.getStrength() }.max())
    }

    @Test
    fun testGetActualLongestBridgeStrength() {
        val comps = ParseComponents(javaClass.getResource("/input.txt").readText())
        val perms = GeneratePermutations(comps)

        println(perms
                .sortedByDescending { it.getStrength() }
                .sortedByDescending { it.components.size }
                .first()
                .getStrength())
    }

}