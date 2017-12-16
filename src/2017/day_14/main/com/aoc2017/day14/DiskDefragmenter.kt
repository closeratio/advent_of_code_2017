package com.aoc2017.day14

import com.aoc2017.day10.CircularList

object DiskDefragmenter {

    fun convertCharToBits(cha: Char): Array<Boolean> {
        val intVal = Integer.parseInt(cha.toString(), 16)

        return arrayOf(
                intVal.and(8) > 0,
                intVal.and(4) > 0,
                intVal.and(2) > 0,
                intVal.and(1) > 0
        )
    }

    fun getDiskState(key: String): DiskState {
        val ds = DiskState()

        IntRange(0, 127).map {
            val rowKey = "$key-$it"
            val knotHash = CircularList.knotHash(rowKey)

            ds.state[it] = knotHash
                    .map { convertCharToBits(it) }
                    .map { listOf(it[0], it[1], it[2], it[3]) }
                    .flatMap { it }
                    .toTypedArray()
        }

        return ds
    }

}