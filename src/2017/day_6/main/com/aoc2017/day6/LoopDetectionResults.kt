package com.aoc2017.day6

class LoopDetectionResults(
        val bankState: BankState,
        val cache: HashMap<BankState, Int> = HashMap(),
        var totalIterationLength: Int = 0
)