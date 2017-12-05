package com.aoc2017.day5

import java.util.*

class ProgramState(
        val jumpSet: Array<Int>,
        var currIndex: Int = 0,
        var iterationCount: Int = 0) {

    override fun equals(other: Any?): Boolean {
        val ps = other as? ProgramState ?: return false
        return currIndex == ps.currIndex
                && jumpSet.contentEquals(ps.jumpSet)
                && iterationCount == ps.iterationCount
    }

    override fun hashCode(): Int {
        return currIndex.hashCode() * Arrays.hashCode(jumpSet) * iterationCount.hashCode()
    }

    override fun toString(): String {
        return "Index $currIndex Iterations $iterationCount Jump set ${jumpSet.map { it.toString() }}"
    }
}