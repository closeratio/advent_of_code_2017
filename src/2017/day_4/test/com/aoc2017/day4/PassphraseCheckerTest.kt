package com.aoc2017.day4

import org.testng.Assert.*
import org.testng.annotations.Test
import java.io.File

class PassphraseCheckerTest {

    @Test
    fun testBasic1() {
        assertTrue(PassphraseChecker.ValidatePassphrase("aa bb cc dd ee"))
    }

    @Test
    fun testBasic2() {
        assertFalse(PassphraseChecker.ValidatePassphrase("aa bb cc dd aa"))
    }

    @Test
    fun testBasic3() {
        assertTrue(PassphraseChecker.ValidatePassphrase("aa bb cc dd aaa"))
    }

    @Test
    fun testActualBasic() {
        println(File(javaClass.getResource("/input.txt").toURI())
                        .readLines()
                        .map { PassphraseChecker.ValidatePassphrase(it.trim()) }
                        .filter { it }
                        .count())
    }

    @Test
    fun testAdvanced1() {
        assertTrue(PassphraseChecker.ValidatePassphraseAdvanced("abcde fghij"))
    }

    @Test
    fun testAdvanced2() {
        assertFalse(PassphraseChecker.ValidatePassphraseAdvanced("abcde xyz ecdab"))
    }

    @Test
    fun testAdvanced3() {
        assertTrue(PassphraseChecker.ValidatePassphraseAdvanced("a ab abc abd abf abj"))
    }

    @Test
    fun testAdvanced4() {
        assertTrue(PassphraseChecker.ValidatePassphraseAdvanced("iiii oiii ooii oooi oooo"))
    }

    @Test
    fun testAdvanced5() {
        assertFalse(PassphraseChecker.ValidatePassphraseAdvanced("oiii ioii iioi iiio"))
    }

    @Test
    fun testActualAsvanced() {
        println(File(javaClass.getResource("/input.txt").toURI())
                .readLines()
                .map { PassphraseChecker.ValidatePassphraseAdvanced(it.trim()) }
                .filter { it }
                .count())
    }

}