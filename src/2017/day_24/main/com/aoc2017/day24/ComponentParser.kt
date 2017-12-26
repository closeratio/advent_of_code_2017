package com.aoc2017.day24

object ComponentParser {

    fun ParseComponent(line: String): Component {
        val spl = line.trim().split("/")
        return Component(spl[0].toInt(), spl[1].toInt())
    }

    fun ParseComponents(data: String): List<Component> {
        return data
                .trim()
                .split("\n")
                .map { ParseComponent(it) }
    }



}