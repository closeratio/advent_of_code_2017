package com.aoc2017.day9

object StreamProcessor {

    fun FilterCancelledCharacters(input: String): String {
        val cancelRegex = Regex("!.")
        return input.replace(cancelRegex, "")
    }

    fun FilterGarbage(input: String): String {
        val garbageRegex = Regex("<[^>]*>")
        return FilterCancelledCharacters(input).replace(garbageRegex, "")
    }

    fun ParseStream(stream: String): Group {
        return Group()
    }

}

