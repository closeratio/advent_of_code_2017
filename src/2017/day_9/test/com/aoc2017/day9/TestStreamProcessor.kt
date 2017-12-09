package com.aoc2017.day9

import org.testng.Assert.*
import org.testng.annotations.Test

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

    @Test
    fun testFilterGarbage() {
        GARBAGE_LINES.forEach {
            assertEquals(StreamProcessor.FilterGarbage(it), "")
        }

        assertEquals(StreamProcessor.FilterGarbage("<woof>hello<woof>"), "hello")
    }

    @Test
    fun testGroupCount() {
6        assertEquals(StreamProcessor.ParseStream(GROUP_LINES[0]).groupCount(), 1)

    }



}