package com.aoc2017.day5

object JumpProcessor {
    fun ParseJumpSet(inputData: String): Array<Int> {
        return inputData
                .trim()
                .split(Regex("\\s+"))
                .map { it.toInt() }
                .toTypedArray()
    }

    fun Execute(ps: ProgramState) {
        val jump = ps.jumpSet[ps.currIndex]
        ps.jumpSet[ps.currIndex]++

        ps.currIndex += jump
        ps.iterationCount++
    }

    fun ExecuteAdvanced(ps: ProgramState) {
        val jump = ps.jumpSet[ps.currIndex]
        if (ps.jumpSet[ps.currIndex] >= 3) {
            ps.jumpSet[ps.currIndex]--
        } else {
            ps.jumpSet[ps.currIndex]++
        }

        ps.currIndex += jump
        ps.iterationCount++
    }

    fun HasFinished(ps: ProgramState): Boolean {
        return ps.currIndex < 0 || ps.currIndex >= ps.jumpSet.size
    }
}