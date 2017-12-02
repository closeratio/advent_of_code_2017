package com.aoc2017.day2

import com.aoc2017.day2.SpreadsheetCalculator.CalculateFunctionSum
import com.aoc2017.day2.SpreadsheetCalculator.CalculateLineChecksum
import com.aoc2017.day2.SpreadsheetCalculator.CalculateLineDivisor
import org.testng.Assert.assertEquals
import org.testng.annotations.Test
import java.io.File

class TestSpreadsheetCalculator {

    @Test
    fun testSingleLine1() {
        assertEquals(CalculateLineChecksum("5\t1\t9\t5"), 8)
    }

    @Test
    fun testSingleLine2() {
        assertEquals(CalculateLineChecksum("7\t5\t3"), 4)
    }

    @Test
    fun testSingleLine3() {
        assertEquals(CalculateLineChecksum("2\t4\t6\t8"), 6)
    }

    @Test
    fun testChecksum1() {
        assertEquals(CalculateFunctionSum(
                "5\t1\t9\t5\n7\t5\t3\n2\t4\t6\t8",
                SpreadsheetCalculator::CalculateLineChecksum),
                18)
    }

    @Test
    fun testChecksum2() {
        println(CalculateFunctionSum(
                File(javaClass.getResource("/input.txt").toURI()).readText(),
                SpreadsheetCalculator::CalculateLineChecksum))
    }

    @Test
    fun testLineDivisor1() {
        assertEquals(CalculateLineDivisor("5\t9\t2\t8"), 4)
    }

    @Test
    fun testLineDivisor2() {
        assertEquals(CalculateLineDivisor("9\t4\t7\t3"), 3)
    }

    @Test
    fun testLineDivisor3() {
        assertEquals(CalculateLineDivisor("3\t8\t6\t5"), 2)
    }

    @Test
    fun testDivisorSum1() {
        assertEquals(
                CalculateFunctionSum("5\t9\t2\t8\n9\t4\t7\t3\n3\t8\t6\t5",
                SpreadsheetCalculator::CalculateLineDivisor),
                9)
    }

    @Test
    fun testDivisorSum2() {
        println(CalculateFunctionSum(
                File(javaClass.getResource("/input.txt").toURI()).readText(),
                SpreadsheetCalculator::CalculateLineDivisor))
    }

}