package com.aoc2017.day21

import java.util.*

class PixelStateGroup private constructor(val states: Array<PixelState>)
    : PixelState() {

    override fun getSize(): Int {
        return states.map { it.getSize() }.sum()
    }

    override fun applyRuleset(rules: List<EnhancementRule>): PixelState {
        return PixelStateGroup(
                states.map { it.applyRuleset(rules) }.toTypedArray())
    }

    override fun getOnPixelCount(): Int {
        return states.map { it.getOnPixelCount() }.sum()
    }

    override fun equals(other: Any?): Boolean {
        val psg = other as? PixelStateGroup ?: return false
        return Arrays.equals(states, psg.states)
    }

    override fun hashCode(): Int {
        return states.map { it.hashCode() }.sum()
    }

    override fun toString(): String {
        return "PixelStateGroup\n${states.map { it.toString() }.joinToString("\n")}"
    }

    companion object {

        fun create(states: Array<PixelState>): PixelStateGroup {
            return PixelStateGroup(states)
        }

    }
}