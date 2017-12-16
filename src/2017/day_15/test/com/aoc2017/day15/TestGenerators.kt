package com.aoc2017.day15

import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class TestGenerators {

    // Test data
    lateinit var genA: Generator
    lateinit var genB: Generator
    lateinit var container: GeneratorContainer

    val genAInput = listOf(
            1092455L,
            1181022009L,
            245556042L,
            1744312007L,
            1352636452L
    )

    val genBInput = listOf(
            430625591L,
            1233683848L,
            1431495498L,
            137874439L,
            285222916L
    )

    @BeforeMethod
    fun beforeGenTest() {
        genA = Generator(65, 16807)
        genB = Generator(8921, 48271)
        container = GeneratorContainer(genA, genB)
    }

    @Test
    fun testGeneratorValue() {
        genAInput.forEach {
            genA.iterate()
            assertEquals(genA.currValue, it)
        }

        genBInput.forEach {
            genB.iterate()
            assertEquals(genB.currValue, it)
        }
    }

    @Test
    fun testGenValuesMatch() {
        container.iterate(5)
        assertEquals(container.matchCount, 1)
    }

    @Test
    fun testGenMatchCount() {
        container.iterate(40_000_000)
        assertEquals(container.matchCount, 588)
    }

    @Test
    fun testActualGenMatchCount() {
        val cont = GeneratorContainer(
                Generator(591, 16807),
                Generator(393, 48271))

        cont.iterate(40_000_000)
        println(cont.matchCount)
    }

    @Test
    fun testAcceptableValue() {
        genA.iterateUsingFactors(4)
        assertEquals(genA.currValue, 1352636452)

        genA.iterateUsingFactors(4)
        assertEquals(genA.currValue, 1992081072)
    }

    @Test
    fun testGenMatchCountComplex1() {
        container.iterateUsingFactors(1055, 4, 8)
        assertEquals(container.matchCount, 0)

        container.iterateUsingFactors(1, 4, 8)
        assertEquals(container.matchCount, 1)
    }

    @Test
    fun testGenMatchCountComplex2() {
        container.iterateUsingFactors(5_000_000, 4, 8)
        assertEquals(container.matchCount, 309)
    }

    @Test
    fun testActualGenMatchCountComplex() {
        val cont = GeneratorContainer(
                Generator(591, 16807),
                Generator(393, 48271))

        cont.iterateUsingFactors(5_000_000, 4, 8)
        println(cont.matchCount)
    }

}