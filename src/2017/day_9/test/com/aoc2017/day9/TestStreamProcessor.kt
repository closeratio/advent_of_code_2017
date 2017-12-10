package com.aoc2017.day9

import org.testng.Assert.*
import org.testng.annotations.Test
import java.io.File

class TestStreamProcessor {

    private val GARBAGE_LINES = listOf(
            "<>",
            "<random characters>",
            "<<<<>",
            "<{!>}>",
            "<!!>",
            "<!!!>>",
            "<{o\"i!a,<{i<a>"
    )

    private val GROUP_LINES = listOf(
            "{}",
            "{{{}}}",
            "{{},{}}",
            "{{{},{},{{}}}}",
            "{<{},{},{{}}>}",
            "{<a>,<a>,<a>,<a>}",
            "{{<a>},{<a>},{<a>},{<a>}}",
            "{{<!>},{<!>},{<!>},{<a>}}"
    )

    private val SCORE_LINES = listOf(
            "{}",
            "{{{}}}",
            "{{},{}}",
            "{{{},{},{{}}}}",
            "{<a>,<a>,<a>,<a>}",
            "{{<ab>},{<ab>},{<ab>},{<ab>}}",
            "{{<!!>},{<!!>},{<!!>},{<!!>}}",
            "{{<a!>},{<a!>},{<a!>},{<ab>}}"
    )

    @Test
    fun testCancelledCharacters() {
        assertEquals(StreamProcessor.FilterCancelledCharacters(GARBAGE_LINES[0]).length, GARBAGE_LINES[0].length)
        assertEquals(StreamProcessor.FilterCancelledCharacters(GARBAGE_LINES[1]).length, GARBAGE_LINES[1].length)
        assertEquals(StreamProcessor.FilterCancelledCharacters(GARBAGE_LINES[2]).length, GARBAGE_LINES[2].length)
        assertEquals(StreamProcessor.FilterCancelledCharacters(GARBAGE_LINES[3]).length, GARBAGE_LINES[3].length - 2)
        assertEquals(StreamProcessor.FilterCancelledCharacters(GARBAGE_LINES[4]).length, GARBAGE_LINES[4].length - 2)
        assertEquals(StreamProcessor.FilterCancelledCharacters(GARBAGE_LINES[5]).length, GARBAGE_LINES[5].length - 4)
        assertEquals(StreamProcessor.FilterCancelledCharacters(GARBAGE_LINES[6]).length, GARBAGE_LINES[6].length - 2)
    }

    @Test
    fun testFilterGarbage() {
        GARBAGE_LINES.forEach {
            assertEquals(StreamProcessor.FilterGarbage(it).first, "")
        }

        assertEquals(StreamProcessor.FilterGarbage("<woof>hello<woof>").first, "hello")
    }

    @Test
    fun testGroupCount() {
        assertEquals(StreamProcessor.ParseStream(GROUP_LINES[0]).first.groupCount(), 1)
        assertEquals(StreamProcessor.ParseStream(GROUP_LINES[1]).first.groupCount(), 3)
        assertEquals(StreamProcessor.ParseStream(GROUP_LINES[2]).first.groupCount(), 3)
        assertEquals(StreamProcessor.ParseStream(GROUP_LINES[3]).first.groupCount(), 6)
        assertEquals(StreamProcessor.ParseStream(GROUP_LINES[4]).first.groupCount(), 1)
        assertEquals(StreamProcessor.ParseStream(GROUP_LINES[5]).first.groupCount(), 1)
        assertEquals(StreamProcessor.ParseStream(GROUP_LINES[6]).first.groupCount(), 5)
        assertEquals(StreamProcessor.ParseStream(GROUP_LINES[7]).first.groupCount(), 2)
    }

    @Test
    fun testGroupScore() {
        val scoreResults = SCORE_LINES.map { StreamProcessor.ParseStream(it).first.score() }

        assertEquals(scoreResults[0], 1)
        assertEquals(scoreResults[1], 6)
        assertEquals(scoreResults[2], 5)
        assertEquals(scoreResults[3], 16)
        assertEquals(scoreResults[4], 1)
        assertEquals(scoreResults[5], 9)
        assertEquals(scoreResults[6], 9)
        assertEquals(scoreResults[7], 3)

    }

    @Test
    fun testActualScore() {
        println(StreamProcessor.ParseStream(
                File(javaClass.getResource("/input.txt").toURI()).readText()).first.score())

    }

    @Test
    fun testGarbageCharCount1() {
        val charCounts = GARBAGE_LINES.map { StreamProcessor.FilterGarbage(it).second }

        assertEquals(charCounts[0], 0)
        assertEquals(charCounts[1], 17)
        assertEquals(charCounts[2], 3)
        assertEquals(charCounts[3], 2)
        assertEquals(charCounts[4], 0)
        assertEquals(charCounts[5], 0)
        assertEquals(charCounts[6], 10)
    }

    @Test
    fun testGarbageCharCount2() {
        val charCounts = GROUP_LINES.map { StreamProcessor.FilterGarbage(it).second }

        assertEquals(charCounts[0], 0)
        assertEquals(charCounts[1], 0)
        assertEquals(charCounts[2], 0)
        assertEquals(charCounts[3], 0)
        assertEquals(charCounts[4], 10)
        assertEquals(StreamProcessor.FilterGarbage(GROUP_LINES[5]).second, 4)
        assertEquals(charCounts[6], 4)
        assertEquals(charCounts[7], 13)
    }

    @Test
    fun testActualCharCount() {
        println(StreamProcessor.ParseStream(
                File(javaClass.getResource("/input.txt").toURI()).readText()).second)

    }



}