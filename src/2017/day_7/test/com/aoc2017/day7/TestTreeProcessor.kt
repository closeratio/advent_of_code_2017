package com.aoc2017.day7

import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import java.io.File

class TestTreeProcessor {

    val discs = ArrayList<Disc>()

    @BeforeTest
    fun setup() {
        discs.addAll(listOf(
                Disc("pbga", 66),
                Disc("xhth", 57),
                Disc("ebii", 61),
                Disc("havc", 66),
                Disc("ktlj", 57),
                Disc("fwft", 72),
                Disc("qoyq", 66),
                Disc("padx", 45),
                Disc("tknk", 41),
                Disc("jptl", 61),
                Disc("ugml", 68),
                Disc("gyxo", 61),
                Disc("cntj", 57)
        ))

        discs[5].addChildren(discs[4], discs[12], discs[1])
        discs[7].addChildren(discs[0], discs[3], discs[6])
        discs[8].addChildren(discs[10], discs[7], discs[5])
        discs[10].addChildren(discs[11], discs[2], discs[9])
    }

    @Test
    fun testParseLine1() {
        assertEquals(TreeProcessor.ParseDiscDef("abcd (12)", HashMap()), Disc("abcd", 12))

        assertEquals(TreeProcessor.ParseDiscDef("efgh (34)", HashMap()), Disc("efgh", 34))
    }

    @Test
    fun testParseLine2() {

        val disc1 = Disc("ijkl", 56)
        val disc2 = Disc("abcd", 0)
        val disc3 = Disc("efgh", 0)
        disc1.addChildren(disc2, disc3)

        val discMap = HashMap<String, Disc>()
        assertEquals(TreeProcessor.ParseLine("ijkl (56) -> abcd, efgh", discMap), disc1)

        assertEquals(discMap["ijkl"], disc1)
        assertEquals(discMap["ijkl"]!!.children, disc1.children)

        assertEquals(discMap["abcd"], disc2)
        assertEquals(discMap["abcd"]!!.parent, disc2.parent)

        assertEquals(discMap["efgh"], disc3)
        assertEquals(discMap["efgh"]!!.parent, disc3.parent)

    }

    @Test
    fun testParseTestData() {

        val discMap = HashMap<String, Disc>()

        File(javaClass.getResource("/test.txt").toURI())
                .readLines()
                .forEach { TreeProcessor.ParseLine(it, discMap) }

        discs.forEach {
            val actual = discMap[it.name]!!
            assertEquals(actual.weight, it.weight)
            assertEquals(actual.parent, it.parent)
            assertEquals(actual.children, it.children)
        }
    }

    @Test
    fun testParseActualData() {
        val discMap = HashMap<String, Disc>()
        File(javaClass.getResource("/input.txt").toURI())
                .readLines()
                .forEach { TreeProcessor.ParseLine(it, discMap) }

        discMap.filter { it.value.parent == null }
                .forEach { println(it.value.name) }

    }

    @Test
    fun testCalculateWeight() {

        assertEquals(discs[10].calculateWeight(), 251)
        assertEquals(discs[7].calculateWeight(), 243)
        assertEquals(discs[5].calculateWeight(), 243)

        assertEquals(discs[8].calculateWeight(), 243 + 243 + 251 + 41)

    }

    @Test
    fun testCalculateRequiredWeight() {

        assertEquals(discs[8].calculateRequiredWeight(), 60)

    }

    @Test
    fun testCalculateActualRequiredWeight() {
        val discMap = HashMap<String, Disc>()
        File(javaClass.getResource("/input.txt").toURI())
                .readLines()
                .forEach { TreeProcessor.ParseLine(it, discMap) }

        discMap.filter { it.value.parent == null }
                .forEach { println(it.value.calculateRequiredWeight()) }
    }

}