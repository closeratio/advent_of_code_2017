package com.aoc2017.day13

import org.testng.Assert.*
import org.testng.annotations.Test
import java.io.File

class TestFirewall {

    val TEST_DATA = arrayOf(
            "0: 3",
            "1: 2",
            "4: 4",
            "6: 4"
    ).joinToString("\n")

    @Test
    fun testParseStructure() {
        val fw = Firewall.Parse(TEST_DATA)

        assertEquals(fw.layers.size, 7)

        assertEquals(fw.layers, listOf(
                Layer(0, 3),
                Layer(1, 2),
                Layer(2, 0),
                Layer(3, 0),
                Layer(4, 4),
                Layer(5, 0),
                Layer(6, 4)))
    }

    @Test
    fun testIterateScanner() {
        val layer = Layer(0, 4)

        assertEquals(layer.scannerIndex, 0)

        layer.moveScanner()
        assertEquals(layer.scannerIndex, 1)

        layer.moveScanner()
        assertEquals(layer.scannerIndex, 2)

        layer.moveScanner()
        assertEquals(layer.scannerIndex, 3)

        layer.moveScanner()
        assertEquals(layer.scannerIndex, 2)

        layer.moveScanner()
        assertEquals(layer.scannerIndex, 1)

        layer.moveScanner()
        assertEquals(layer.scannerIndex, 0)

        layer.moveScanner()
        assertEquals(layer.scannerIndex, 1)

        layer.moveScanner()
        assertEquals(layer.scannerIndex, 2)
    }

    @Test
    fun testIterateFirewallScanners() {
        val fw = Firewall.Parse(TEST_DATA)

        fw.moveScanners()
        assertEquals(fw.layers[0].scannerIndex, 1)
        assertEquals(fw.layers[1].scannerIndex, 1)
        assertEquals(fw.layers[4].scannerIndex, 1)
        assertEquals(fw.layers[6].scannerIndex, 1)

        fw.moveScanners()
        assertEquals(fw.layers[0].scannerIndex, 2)
        assertEquals(fw.layers[1].scannerIndex, 0)
        assertEquals(fw.layers[4].scannerIndex, 2)
        assertEquals(fw.layers[6].scannerIndex, 2)

        fw.moveScanners()
        assertEquals(fw.layers[0].scannerIndex, 1)
        assertEquals(fw.layers[1].scannerIndex, 1)
        assertEquals(fw.layers[4].scannerIndex, 3)
        assertEquals(fw.layers[6].scannerIndex, 3)
    }

    @Test
    fun testMovePacket() {
        val fw = Firewall.Parse(TEST_DATA)

        assertEquals(fw.packetIndex, -1)

        fw.movePacket()
        assertEquals(fw.packetIndex, 0)

        fw.movePacket()
        assertEquals(fw.packetIndex, 1)

        fw.movePacket()
        assertEquals(fw.packetIndex, 2)

        fw.movePacket()
        assertEquals(fw.packetIndex, 3)

        fw.movePacket()
        assertEquals(fw.packetIndex, 4)

        fw.movePacket()
        assertEquals(fw.packetIndex, 5)

        fw.movePacket()
        assertEquals(fw.packetIndex, 6)

        fw.movePacket()
        assertEquals(fw.packetIndex, 7)

        fw.movePacket()
        assertEquals(fw.packetIndex, 7)
    }

    @Test
    fun testPacketCaught() {
        val fw = Firewall.Parse(TEST_DATA)

        IntRange(0, 7).forEach {
            fw.movePacket()
            if (it == 0 || it == 6) {
                assertTrue(fw.isPacketCaught())
            } else {
                assertFalse(fw.isPacketCaught())
            }
            fw.moveScanners()
        }
    }

    @Test
    fun testSeverity() {
        val fw = Firewall.Parse(TEST_DATA)

        assertEquals(fw.severity, 0)

        IntRange(0, 7).forEach {
            fw.iterate()
            if (it >= 6) {
                assertEquals(fw.severity, 24)
            } else {
                assertEquals(fw.severity, 0)
            }
        }
    }

    @Test
    fun testTravel() {
        val fw = Firewall.Parse(TEST_DATA)
        fw.travel()

        assertEquals(fw.packetIndex, 7)
        assertEquals(fw.severity, 24)
    }

    @Test
    fun testActualTravel() {
        val fw = Firewall.Parse(File(javaClass.getResource("/input.txt").toURI()).readText())

        fw.travel()

        println(fw.severity)
    }

    @Test
    fun testOptimalDelay() {
        val fw = Firewall.Parse(TEST_DATA)

        assertEquals(fw.getOptimalDelay(), 10)
    }

    @Test
    fun testActualOptimalDelay() {
        val fw = Firewall.Parse(File(javaClass.getResource("/input.txt").toURI()).readText())

        println(fw.getOptimalDelay())
    }

}