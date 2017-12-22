package com.aoc2017.day21

abstract class PixelState {

    abstract fun applyRuleset(rules: List<EnhancementRule>): PixelState

    abstract fun getOnPixelCount(): Int

    abstract fun getSize(): Int

}