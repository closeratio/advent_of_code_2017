package com.aoc2017.day9

class Group(
        val children: MutableList<Group> = ArrayList(),
        val level: Int = 1) {

    fun groupCount(): Int {
        return 0
    }
}

