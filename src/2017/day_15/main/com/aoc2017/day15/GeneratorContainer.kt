package com.aoc2017.day15

class GeneratorContainer(
        val genA: Generator,
        val genB: Generator
) {

    var matchCount = 0

    fun iterate(count: Int = 1) {
        var i = 0

        while (i < count) {
            genA.iterate()
            genB.iterate()

            updateMatch()

            i++
        }
    }

    private fun updateMatch() {
        val aVal = genA.currValue.toInt().and(0x0000FFFF)
        val bVal = genB.currValue.toInt().and(0x0000FFFF)

        if (aVal == bVal) {
            matchCount++
        }
    }

    fun iterateUsingFactors(count: Int = 1, factorA: Long, factorB: Long) {
        var i = 0

        while (i < count) {
            genA.iterateUsingFactors(factorA)
            genB.iterateUsingFactors(factorB)

            updateMatch()

            i++
        }
    }

}