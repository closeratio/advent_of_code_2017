package com.aoc2017.day6

import org.testng.Assert.assertEquals
import org.testng.annotations.Test
import java.io.File

class TestReallocationProcessor {

    @Test
    fun testParseBanks() {
        assertEquals(ReallocationProcessor.ParseBanks("0\t2\t7\t0"), BankState(0, 2, 7, 0))
    }

    @Test
    fun testGetNextIndex() {
        val testBanks = BankState(0, 2, 7, 0)

        assertEquals(ReallocationProcessor.GetNextReallocationIndex(testBanks), 2)
    }

    @Test
    fun testPass1() {
        val testBanks = BankState(0, 2, 7, 0)

        ReallocationProcessor.Reallocate(testBanks)
        assertEquals(testBanks, BankState(2, 4, 1, 2))
    }

    @Test
    fun testPass2() {
        val testBanks = BankState(2, 4, 1, 2)

        ReallocationProcessor.Reallocate(testBanks)
        assertEquals(testBanks, BankState(3, 1, 2, 3))
    }

    @Test
    fun testPass3() {
        val testBanks = BankState(3, 1, 2, 3)

        ReallocationProcessor.Reallocate(testBanks)
        assertEquals(testBanks, BankState(0, 2, 3, 4))
    }

    @Test
    fun testPass4() {
        val testBanks = BankState(0, 2, 3, 4)

        ReallocationProcessor.Reallocate(testBanks)
        assertEquals(testBanks, BankState(1, 3, 4, 1))
    }

    @Test
    fun testPass5() {
        val testBanks = BankState(1, 3, 4, 1)

        ReallocationProcessor.Reallocate(testBanks)
        assertEquals(testBanks, BankState(2, 4, 1, 2))
    }

    @Test
    fun testLoopDetected() {
        val testBanks = BankState(0, 2, 7, 0)
        assertEquals(ReallocationProcessor.Process(testBanks).totalIterationLength, 5)
    }

    @Test
    fun testActualLoopDetection() {
        println(ReallocationProcessor.Process(ReallocationProcessor.ParseBanks(
                File(javaClass.getResource("/input.txt").toURI()).readText())).totalIterationLength)
    }

    @Test
    fun testLoopLength() {
        val testBanks = BankState(0, 2, 7, 0)
        assertEquals(ReallocationProcessor.GetLoopCount(testBanks), 4)
    }

    @Test
    fun testActualLoopLength() {
        println(ReallocationProcessor.GetLoopCount(ReallocationProcessor.ParseBanks(
                File(javaClass.getResource("/input.txt").toURI()).readText())))
    }

}