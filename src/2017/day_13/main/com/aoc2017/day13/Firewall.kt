package com.aoc2017.day13

import java.io.IOException

class Firewall(
        val layers: List<Layer>
) {
    var packetIndex = -1

    var severity = 0
    var wasCaught = false

    companion object {

        fun Parse(data: String): Firewall {
            val lines = data.trim().split("\n").map { it.trim() }

            val lineRegex = Regex("(\\d+): (\\d+)")
            val layers = ArrayList<Layer>()

            lines.forEach {
                val result = lineRegex.matchEntire(it) ?: throw IOException("Bad line format: \"$it\"")
                val depth = result.groupValues[1].toInt()
                val range = result.groupValues[2].toInt()

                while (layers.size < depth) {
                    layers.add(Layer(layers.size, 0))
                }

                layers.add(Layer(layers.size, range))
            }

            return Firewall(layers)
        }
    }

    fun moveScanners() {
        layers.forEach { it.moveScanner() }
    }

    fun movePacket() {
        if (packetIndex < layers.size) {
            packetIndex++
        }
    }

    fun isPacketCaught(): Boolean {
        return if (packetIndex == -1
                || packetIndex >= layers.size
                || layers[packetIndex].range == 0) {
            false
        } else {
            layers[packetIndex].scannerIndex == 0
        }
    }

    fun updateSeverity() {
        if (isPacketCaught()) {
            wasCaught = true
            severity += packetIndex * layers[packetIndex].range
        }
    }

    fun iterate() {
        movePacket()
        updateSeverity()
        moveScanners()
    }

    fun travel() {
        while (packetIndex < layers.size) {
            iterate()
        }
    }

    fun getOptimalDelay(): Int {
        var currDelay = 0

        var caught = true

        while (caught) {

            val testFW = Firewall(layers.map { layer ->
                Layer(layer.depth, layer.range).apply {
                    scannerDir = layer.scannerDir
                    scannerIndex = layer.scannerIndex
                }
            })

            testFW.travel()

            caught = testFW.wasCaught

            if (caught) {
                currDelay++
                moveScanners()
            }
        }

        return currDelay
    }


}

