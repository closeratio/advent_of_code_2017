package com.aoc2017.day6

object ReallocationProcessor {

    fun ParseBanks(input: String): BankState {
        return BankState(*input
                .trim()
                .split(Regex("\\s+"))
                .map { it.toInt() }
                .toIntArray())
    }

    fun GetNextReallocationIndex(banks: BankState): Int {
        return banks.banks
                .mapIndexed { index, item -> Pair(index, item) }
                .maxBy { it.second }
                ?.first ?: 0
    }

    fun Reallocate(banks: BankState) {
        var index = GetNextReallocationIndex(banks)
        var remainingAmount = banks.banks[index]
        banks.banks[index] = 0

        while (remainingAmount > 0) {
            index = (index + 1) % banks.banks.size
            banks.banks[index]++

            remainingAmount--
        }
    }

    fun Process(initialBanks: BankState): LoopDetectionResults {
        val results = LoopDetectionResults(initialBanks.clone())
        val cache = results.cache
        val state = results.bankState

        while (!cache.contains(results.bankState)) {
            val clone = state.clone()
            cache[clone] = results.totalIterationLength
            results.totalIterationLength++

            Reallocate(state)
        }

        return results
    }

    fun GetLoopCount(testBanks: BankState): Int {
        val results = Process(testBanks)
        return results.totalIterationLength - results.cache[results.bankState]!!
    }
}

