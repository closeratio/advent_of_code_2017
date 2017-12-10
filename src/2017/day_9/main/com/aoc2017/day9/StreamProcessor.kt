package com.aoc2017.day9

import java.util.*

object StreamProcessor {

    fun FilterCancelledCharacters(input: String): String {
        val cancelRegex = Regex("!.")
        return input.replace(cancelRegex, "")
    }

    fun FilterGarbage(input: String): Pair<String, Int> {
        val garbageRegex = Regex("<([^>]*)>")
        val postCancelled = FilterCancelledCharacters(input)
        val postFiltered = postCancelled.replace(garbageRegex, "")
        val garbageChars = garbageRegex.findAll(postCancelled).map { it.groupValues[1].length }.sum()

        return Pair(postFiltered, garbageChars)
    }

    fun ParseStream(stream: String): Pair<Group, Int> {
        val filterResult = FilterGarbage(stream.trim())
        val groupStack = Stack<Group>()
        var lastPoppedGroup: Group? = null

        filterResult.first.forEach {
            when (it) {
                '{' -> {
                    if (groupStack.size > 0) {
                        val last = groupStack.last()
                        val group = Group(level = last.level + 1, parent = last)
                        last.children.add(group)
                        groupStack.push(group)
                    } else {
                        groupStack.push(Group())
                    }
                }
                '}' -> {
                    if (groupStack.size > 0) {
                        lastPoppedGroup = groupStack.pop()
                    } else {
                        throw RuntimeException("Unexpected close bracket")
                    }
                }
                ',' -> {}
                else -> throw RuntimeException("Unexpected character: $it")
            }
        }

        return Pair(
                lastPoppedGroup?: throw RuntimeException("No group found in stream"),
                filterResult.second)
    }

}

