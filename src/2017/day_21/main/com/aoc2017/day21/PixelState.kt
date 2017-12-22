package com.aoc2017.day21

import java.util.*
import kotlin.math.pow

class PixelState(val state: Array<Array<Boolean>>) {

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

    fun applyRuleset(rules: Map<PixelState, PixelState>): PixelState {
        return rules[this] ?: throw RuntimeException("Can't find matching rule for $this")
    }

    fun iterate(rules: Map<PixelState, PixelState>): PixelState {
        return combine(separate().map { it.applyRuleset(rules) })
    }

    fun separate(): List<PixelState> {
        return if (state.size == 2 || state.size == 3) {
            listOf(this)
        } else if (state.size % 2 == 0) {
            val indices = IntRange(0, state.size - 1)
                    .filter { it % 2 == 0 }

            indices.map { r ->
                indices.map { c ->
                    PixelState(arrayOf(
                            arrayOf(state[r][c], state[r][c + 1]),
                            arrayOf(state[r + 1][c], state[r + 1][c + 1])
                    ))
                }
            }.flatMap { it }
        } else if (state.size % 3 == 0) {
            val indices = IntRange(0, state.size - 1)
                    .filter { it % 3 == 0 }

            indices.map { r ->
                indices.map { c ->
                    PixelState(arrayOf(
                            arrayOf(state[r][c], state[r][c + 1], state[r][c + 2]),
                            arrayOf(state[r + 1][c], state[r + 1][c + 1], state[r + 1][c + 2]),
                            arrayOf(state[r + 2][c], state[r + 2][c + 1], state[r + 2][c + 2])
                    ))
                }
            }.flatMap { it }
        } else {
            throw RuntimeException("State is not divisible by 2 or 3: ${state.size}")
        }
    }

    fun getOnPixelCount(): Int {
        return state.flatMap { it.map { it } }
                .filter { it }
                .count()
    }

    override fun equals(other: Any?): Boolean {
        val ps = other as? PixelState ?: return false
        if (state.size != ps.state.size) return false
        if (this === ps) return true

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

    companion object {

        fun combine(states: List<PixelState>): PixelState {
            return if (states.size == 1) {
                states[0]
            } else {
                val sideLength = states.size.toDouble().pow(0.5).toInt()
                val stateSize = states[0].state.size

                val size = sideLength * stateSize

                PixelState(Array(size) { rowIndex ->
                    val stateOffset = (rowIndex / stateSize) * sideLength

                    Array(size) { colIndex ->
                        val unitIndex = stateOffset + colIndex / stateSize
                        val stateRow = rowIndex % stateSize
                        val stateCol = colIndex % stateSize

                        states[unitIndex].state[stateRow][stateCol]
                    }
                })
            }
        }

    }
}