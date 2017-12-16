package com.aoc2017.day15

class Generator(
        val startValue: Long,
        val factor: Long
) {

    var currValue = startValue

    fun iterate() {
        currValue = (currValue * factor) % 2147483647L
    }

    fun iterateUsingFactors(mod: Long) {
        do {
            iterate()
        } while ((currValue % mod) != 0L)
    }

}