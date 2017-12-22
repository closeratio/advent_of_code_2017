package com.aoc2017.day21

abstract class PixelState {

    abstract fun applyRuleset(rules: Map<PixelState, PixelState>): PixelState

    abstract fun getOnPixelCount(): Int

    abstract fun getSize(): Int

    abstract fun getNodeCount(): Int

}