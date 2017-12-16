package com.aoc2017.day14

class DiskState {

    val state = Array(128) {
        Array(128) {
            false
        }
    }

    fun getUsedCount(): Int {
        return state.flatMap { it.toList() }
                .filter { it }
                .size
    }

    fun getRegionCount(): Int {
        val openSet = state
                .mapIndexed { rowInd, row ->
                    row.mapIndexed { colInd, used ->
                        if (used) {
                            Vec2(colInd, rowInd)
                        } else {
                            null
                        }
                    }.filterNotNull()
                }
                .flatMap { it }
                .toHashSet()

        val regions = HashSet<Set<Vec2>>()

        HashSet(openSet).forEach {
            
        }

        return regions.size
    }

}