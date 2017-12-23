package com.aoc2017.day23

object AssemblySimulation {

    fun simulate(): Int {

        var target = 81

        var factorCount = 0

        target = (target * 100) + 100000
        val upperLimit = target + 17000

        do {
            var factor = 2

            do {

                if (target % factor == 0) {
                    ++factorCount
                    break
                }

                ++factor
            } while ((factor * 2) <= target)

            target += 17
        } while (target <= upperLimit)

        return factorCount
    }

}