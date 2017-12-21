package com.aoc2017.day21

import javafx.scene.input.PickResult
import java.util.*

class PixelState(
        val state: Array<Array<Boolean>>
) {

    fun flipState(): PixelState {
        return PixelState(state.map {
            it.reversedArray()
        }.toTypedArray())
    }

    fun rotateState(): PixelState {
        val newArr = Array(state.size) { Array(state.size) { false } }

        state.forEachIndexed { rowIndex, arr ->
            arr.forEachIndexed { colIndex, bVal ->
                newArr[colIndex][state.size - rowIndex - 1] = bVal
            }
        }

        return PixelState(newArr)
    }

    fun getPermutations(): Array<PixelState> {
        val perms = mutableListOf<PixelState>()

        var rot = this

        repeat(4) {
            perms.add(rot)
            perms.add(rot.flipState())
            rot = rot.rotateState()
        }

        return perms.distinct().toTypedArray()
    }

    fun applyRuleset(rules: List<EnhancementRule>): PixelState {

        if (state.size % 2 == 0) {

        } else if (state.size % 3 == 0) {

        } else {
            throw RuntimeException("State is not divisible by 2 or 3: ${state.size}")
        }

        return this
    }

    override fun equals(other: Any?): Boolean {
        val ps = other as? PixelState ?: return false
        if (state.size != ps.state.size) return false

        state.forEachIndexed { r, arr ->
            arr.forEachIndexed { c, bval ->
                if (bval != ps.state[r][c]) {
                    return false
                }
            }
        }

        return true
    }

    override fun hashCode(): Int {
        return state.map {
            Arrays.hashCode(it)
        }.sum()
    }

    override fun toString(): String {
        return "PixelState:\n${state.map { it.map { if (it) "#" else "." }.joinToString("") }.joinToString("\n")}"
    }
}