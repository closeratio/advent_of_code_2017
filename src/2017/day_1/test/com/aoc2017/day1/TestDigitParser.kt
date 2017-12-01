package com.aoc2017.day1

import org.testng.Assert
import org.testng.annotations.Test
import java.io.File

class TestDigitParser {

    @Test
    fun simpleTest1() {
        Assert.assertEquals(DigitParser.parseDigits("1122"), 3)
    }

    @Test
    fun simpleTest2() {
        Assert.assertEquals(DigitParser.parseDigits("1111"), 4)
    }

    @Test
    fun simpleTest3() {
        Assert.assertEquals(DigitParser.parseDigits("1234"), 0)
    }

    @Test
    fun simpleTest4() {
        Assert.assertEquals(DigitParser.parseDigits("91212129"), 9)
    }

    @Test
    fun actualSimpleTest() {
        println(DigitParser.parseDigits(
                File(javaClass.getResource("/input.txt").toURI()).readText()
        ))
    }

    @Test
    fun complexTest1() {
        Assert.assertEquals(DigitParser.parseDigitsComplex("1212"), 6)
    }

    @Test
    fun complexTest2() {
        Assert.assertEquals(DigitParser.parseDigitsComplex("1221"), 0)
    }

    @Test
    fun complexTest3() {
        Assert.assertEquals(DigitParser.parseDigitsComplex("123425"), 4)
    }

    @Test
    fun complexTest4() {
        Assert.assertEquals(DigitParser.parseDigitsComplex("123123"), 12)
    }

    @Test
    fun complexTest5() {
        Assert.assertEquals(DigitParser.parseDigitsComplex("12131415"), 4)
    }

    @Test
    fun actualComplexTest() {
        println(DigitParser.parseDigitsComplex(
                File(javaClass.getResource("/input.txt").toURI()).readText()
        ))
    }

}