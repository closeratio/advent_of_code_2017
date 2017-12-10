package com.aoc2017.day9

class Group(
        val children: MutableList<Group> = ArrayList(),
        val level: Int = 1,
        val parent: Group? = null) {

    fun groupCount(): Int {
        return 1 + children.map { it.groupCount() }.sum()
    }

    fun score(): Int {
        return level + children.map { it.score() }.sum()
    }
}

