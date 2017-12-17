package com.aoc2017.day17

class SpinLock(
        val stepSize: Int
) {

    val values = ArrayList<Int>(50_000_000).apply {
        add(0)
    }

    var currPos = 0

    fun insertNext() {
        val next = values.max()!! + 1

        val ind = ((currPos + stepSize) % values.size) + 1
        currPos = ind
        values.add(ind, next)
    }

}