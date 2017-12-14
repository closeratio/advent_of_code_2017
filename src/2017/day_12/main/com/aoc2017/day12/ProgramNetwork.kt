package com.aoc2017.day12

import java.io.IOException

class ProgramNetwork {
    val programs = HashMap<Int, Program>()

    fun parseLine(line: String) {
        val lineRegex = Regex("^(\\d+) <-> ([0-9 ,]+)$")

        val result = lineRegex.find(line.trim()) ?: throw IOException("Line doesn't match expected format: \"$line\"")

        val leftProgID = result.groupValues[1].toInt()

        val rightProgIDs = result.groupValues[2]
                .trim()
                .split(",")
                .map { it.trim().toInt() }

        val leftProg = programs.getOrPut(leftProgID, { Program(leftProgID) })

        rightProgIDs.forEach {
            val rightProg = programs.getOrPut(it, { Program(it) })
            leftProg.addConnection(rightProg)
        }
    }

    fun findConnectedPrograms(target: Int): Set<Program> {
        val openSet = mutableSetOf(programs[target]!!)
        val closedSet = mutableSetOf<Program>()

        while (!openSet.isEmpty()) {
            val curr = openSet.first()
            openSet.remove(curr)
            closedSet.add(curr)

            curr.connections
                    .filter { it !in closedSet }
                    .forEach { openSet.add(it) }
        }

        return closedSet
    }

    fun findGroupCount(): Int {
        val remainingProgs = HashSet(programs.values)

        var progCount = 0
        while (remainingProgs.size > 0) {
            progCount++

            findConnectedPrograms(remainingProgs.first().id)
                    .forEach { remainingProgs.remove(it) }
        }

        return progCount
    }

}

