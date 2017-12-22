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

}