package com.aoc2017.day12

class Program(
        val id: Int
) {

    val connections = HashSet<Program>()

    override fun equals(other: Any?): Boolean {
        val pr = other as? Program ?: return false
        return id == pr.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Program [$id]"
    }

    fun addConnection(prog: Program) {

        if (prog != this && !connections.contains(prog)) {
            connections.add(prog)
            prog.addConnection(this)
        }
    }
}