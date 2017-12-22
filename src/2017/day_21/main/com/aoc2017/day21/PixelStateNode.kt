package com.aoc2017.day21

import java.util.*

class PixelStateNode private constructor(val state: Array<Array<Boolean>>)
    : PixelState() {

    fun flipState(): PixelStateNode {
        return PixelStateNode(state.map {
            it.reversedArray()
        }.toTypedArray())
    }

    fun rotateState(): PixelStateNode {
        val newArr = Array(state.size) { Array(state.size) { false } }

        state.forEachIndexed { rowIndex, arr ->
            arr.forEachIndexed { colIndex, bVal ->
                newArr[colIndex][state.size - rowIndex - 1] = bVal
            }
        }

        return PixelStateNode(newArr)
    }

    fun getPermutations(): Array<PixelStateNode> {
        val perms = mutableListOf<PixelStateNode>()

        var rot = this

        repeat(4) {
            perms.add(rot)
            perms.add(rot.flipState())
            rot = rot.rotateState()
        }

        return perms.distinct().toTypedArray()
    }

    override fun applyRuleset(rules: List<EnhancementRule>): PixelState {
        val perms = getPermutations()

        perms.forEach { perm ->
            rules.forEach { rule ->
                if (perm == rule.input) {
                    return rule.output
                }
            }
        }

        throw RuntimeException("Can't find matching rule for $this")
    }

    override fun getOnPixelCount(): Int {
        return state.flatMap { it.map { it } }
                .filter { it }
                .count()
    }
    override fun getSize(): Int {
        return state.size
    }

    override fun equals(other: Any?): Boolean {
        val ps = other as? PixelStateNode ?: return false
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
        return "PixelStateNode:\n${state.map { it.map { if (it) "#" else "." }.joinToString("") }.joinToString("\n")}"
    }

    companion object {

        fun create(state: Array<Array<Boolean>>): PixelState {
            return if (state.size == 2 || state.size == 3) {
                PixelStateNode(state)
            } else if (state.size == 4) {
                 PixelStateGroup.create(arrayOf(
                         PixelStateNode(arrayOf(
                                 arrayOf(state[0][0], state[0][1]),
                                 arrayOf(state[1][0], state[1][1])
                         )),
                         PixelStateNode(arrayOf(
                                 arrayOf(state[0][2], state[0][3]),
                                 arrayOf(state[1][2], state[1][3])
                         )),
                         PixelStateNode(arrayOf(
                                 arrayOf(state[2][0], state[2][1]),
                                 arrayOf(state[3][0], state[3][1])
                         )),
                         PixelStateNode(arrayOf(
                                 arrayOf(state[2][2], state[2][3]),
                                 arrayOf(state[3][2], state[3][3])
                         ))
                 ))
            } else {
                throw RuntimeException("Cannot create pixel state with size: ${state.size}")
            }
        }

    }
}