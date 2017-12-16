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
        val allSquares = state
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

        val openSet = HashSet(allSquares)

        while (openSet.isNotEmpty()) {
            val currRegion = HashSet<Vec2>()
            val searchList = ArrayList<Vec2>()
            searchList.add(openSet.first())

            while (searchList.isNotEmpty()) {
                val curr = searchList[0]
                openSet.remove(curr)
                currRegion.add(curr)
                searchList.remove(curr)

                curr.getAdjacent()
                        .filter { it in openSet }
                        .forEach { searchList.add(it) }
            }

            regions.add(currRegion)
        }

        return regions.size
    }

}