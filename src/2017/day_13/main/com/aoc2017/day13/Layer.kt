package com.aoc2017.day13

import com.aoc2017.day13.Direction.*

class Layer(
        val depth: Int,
        val range: Int
) {

    var scannerIndex = 0
    var scannerDir = DOWN

    fun moveScanner() {
        if (range == 0) return

        when (scannerDir) {
            DOWN -> scannerIndex++
            UP -> scannerIndex--
            else -> throw RuntimeException("Unhandled scanner direction: $scannerDir")
        }

        if (scannerIndex == 0) {
            scannerDir = DOWN
        } else if (scannerIndex == range - 1) {
            scannerDir = UP
        }
    }

    override fun equals(other: Any?): Boolean {
        val layer = other as? Layer ?: return false
        return depth == layer.depth && range == layer.range
    }

    override fun hashCode(): Int {
        return depth.hashCode() * range.hashCode()
    }

    override fun toString(): String {
        return "Layer (depth $depth, range $range)"
    }
}

