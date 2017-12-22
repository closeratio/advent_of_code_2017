package com.aoc2017.day22

import org.testng.Assert.assertEquals
import org.testng.Assert.assertNull
import org.testng.annotations.Test

class TestSporificaModel {

    private val TEST_MAP =
            "..#\n" +
            "#..\n" +
            "..."

    @Test
    fun testParseMap() {
        val model = SporificaModel.create(TEST_MAP)

        assertNull(model.positionMap[Vec2(-2, 1)])
        assertNull(model.positionMap[Vec2(-1, 2)])
        assertNull(model.positionMap[Vec2(2, 1)])
        assertNull(model.positionMap[Vec2(1, 2)])

        assertEquals(model.positionMap[Vec2(-1, 1)], false)
        assertEquals(model.positionMap[Vec2(1, 1)], true)
        assertEquals(model.positionMap[Vec2(-1, 0)], true)
        assertEquals(model.positionMap[Vec2(0, 0)], false)
    }

    @Test
    fun testIterate1() {
        val model = SporificaModel.create(TEST_MAP)

        model.iterate()
        assertEquals(model.positionMap[Vec2.ZERO], true )
        assertEquals(model.positionMap[Vec2(-1, 0)], true )
        assertEquals(model.positionMap[Vec2(1, 1)], true )
        assertEquals(model.positionMap[Vec2(1, 0)], false)
    }

    @Test
    fun testInfectionCount() {
        val model = SporificaModel.create(TEST_MAP)

        model.iterate(10000)
        assertEquals(model.infectionCount, 5587)
    }

    @Test
    fun testActualInfectionCount() {
        val model = SporificaModel.create(javaClass.getResource("/input.txt").readText())

        model.iterate(10000)
        println(model.infectionCount)
    }

    @Test
    fun testAdvancedInfectionCount1() {
        val model = AdvancedSporificaModel.create(TEST_MAP)

        model.iterate(100)
        assertEquals(model.infectionCount, 26)
    }

    @Test
    fun testAdvancedInfectionCount2() {
        val model = AdvancedSporificaModel.create(TEST_MAP)

        model.iterate(10000000)
        assertEquals(model.infectionCount, 2511944)
    }

    @Test
    fun testActualAdvancedInfectionCount() {
        val model = AdvancedSporificaModel.create(javaClass.getResource("/input.txt").readText())

        model.iterate(10000000)
        println(model.infectionCount)
    }

}