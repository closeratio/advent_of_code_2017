package com.aoc2017.day19

import com.aoc2017.day19.Direction.DOWN
import com.aoc2017.day19.Direction.RIGHT
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class TestRouter {

    private val PUZZLE_INPUT =
            "     |          \n" +
            "     |  +--+    \n" +
            "     A  |  C    \n" +
            " F---|----E|--+ \n" +
            "     |  |  |  D \n" +
            "     +B-+  +--+ \n"

    @Test
    fun testDetermineInitialPosition() {
        assertEquals(Router(PUZZLE_INPUT).getInitialPosition(), Vec2(5, 5))

        assertEquals(Router("  |   \n  |   \n").getInitialPosition(), Vec2(2, 1))
    }

    @Test
    fun testAssembleGrid() {
        val router = Router(PUZZLE_INPUT)
        assertEquals(router.getGridWidth(), 16)
        assertEquals(router.getGridHeight(), 6)
    }

    @Test
    fun testMove() {
        val router = Router(PUZZLE_INPUT)

        router.move()
        assertEquals(router.currentPosition, Vec2(5, 4))
    }

    @Test
    fun testIterate() {
        val router = Router(PUZZLE_INPUT)

        router.iterate()
        assertEquals(router.currentPosition, Vec2(5, 4))
        assertEquals(router.direction, DOWN)

        router.iterate()
        assertEquals(router.currentPosition, Vec2(5, 3))
        assertEquals(router.direction, DOWN)
        assertEquals(router.charList, listOf("A"))

        router.iterate()
        assertEquals(router.currentPosition, Vec2(5, 2))
        assertEquals(router.direction, DOWN)

        router.iterate()
        assertEquals(router.currentPosition, Vec2(5, 1))
        assertEquals(router.direction, DOWN)

        router.iterate()
        assertEquals(router.currentPosition, Vec2(5, 0))
        assertEquals(router.direction, RIGHT)

        router.iterate()
        assertEquals(router.currentPosition, Vec2(6, 0))
        assertEquals(router.direction, RIGHT)
        assertEquals(router.charList, listOf("A", "B"))
    }

    @Test
    fun testTraverse() {
        val router = Router(PUZZLE_INPUT)
        router.traverse()
        assertEquals(router.charList, listOf("A", "B", "C", "D", "E", "F"))
        assertEquals(router.iterCount, 38)
    }

    @Test
    fun testActualTraverse() {
        val router = Router(javaClass.getResource("/input.txt").readText())
        router.traverse()
        println(router.charList.joinToString(""))
        println(router.iterCount)
    }

}