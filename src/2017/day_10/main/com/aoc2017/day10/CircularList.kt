package com.aoc2017.day10

class CircularList(
        listLength: Int = 256
) {

    val list = IntRange(0, listLength - 1).toMutableList()
    var currPosition = 0
    var skipSize: Int = 0

    fun selectElements(length: Int): List<Int> {
        return IntRange(currPosition, currPosition + length - 1).map {
            list[it % list.size]
        }
    }

    fun reverse(length: Int) {
        val reversed = selectElements(length).reversed()

        IntRange(currPosition, currPosition + length - 1).forEachIndexed { index, pos ->
            list[pos % list.size] = reversed[index]
        }

        currPosition = (currPosition + length + skipSize) % list.size
        skipSize++
    }

    fun getSimpleHash(): Int {
        return list[0] * list[1]
    }

    fun getKnotHash(data: String): String {
        val lengths = generateLengths(data)

        IntRange(1, 64).forEach {
            lengths.forEach { length ->
                reverse(length)
            }
        }

        val denseHash = list.windowed(16, 16) {
            var xor = 0
            it.forEach { xor = xor.xor(it) }
            xor
        }

        return denseHash.map {
            val prefix = if (it < 16) {
                "0"
            } else {
                ""
            }
            prefix + Integer.toHexString(it)
        }.joinToString("")
    }

    companion object {
        fun getLengthsFromString(data: String): List<Int> {
            return data.trim()
                    .map { it.toInt() }
        }

        fun generateLengths(data: String): List<Int> {
            return getLengthsFromString(data)
                    .toMutableList()
                    .apply {
                        addAll(listOf(17, 31, 73, 47, 23))
                    }

        }

        fun knotHash(key: String): String {
            return CircularList().getKnotHash(key)
        }
    }

}