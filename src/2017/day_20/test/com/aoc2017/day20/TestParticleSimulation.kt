package com.aoc2017.day20

import org.testng.Assert.assertEquals
import org.testng.annotations.Test
import kotlin.math.pow

class TestParticleSimulation {

    private val TEST_INPUT =
            "p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>\n" +
            "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>\n"

    @Test
    fun testParseParticleDef() {
        val ps = ParticleSimulation(TEST_INPUT)
        assertEquals(ps.particleCount, 2)

        assertEquals(ps.particles[0].pos, Vec3(3, 0, 0))
        assertEquals(ps.particles[1].pos, Vec3(4, 0, 0))

        assertEquals(ps.particles[0].vel, Vec3(2, 0, 0))
        assertEquals(ps.particles[1].vel, Vec3(0, 0, 0))

        assertEquals(ps.particles[0].acc, Vec3(-1, 0, 0))
        assertEquals(ps.particles[1].acc, Vec3(-2, 0, 0))
    }

    @Test
    fun testVecMagnitude() {
        assertEquals(Vec3(1, 2, -3).length(), (1 + 2 * 2 + 3 * 3).toDouble().pow(0.5))
    }

    @Test
    fun testManhattanDistance() {
        assertEquals(Vec3(1, -2, -3).manhattanLength(), 6L)
    }

    @Test
    fun testGetClosestParticle() {
        val ps = ParticleSimulation(TEST_INPUT)
        ps.iterate(1000)

        assertEquals(ps.getClosestParticleToZero(), 0)
    }

    @Test
    fun testGetActualClosestParticle() {
        val ps = ParticleSimulation(javaClass.getResource("/input.txt").readText())
        ps.iterate(10000)

        println(ps.getClosestParticleToZero())
    }

    @Test
    fun testGetRemainingParticleCount() {
        val ps = ParticleSimulation(javaClass.getResource("/input.txt").readText())

        ps.iterate(10000, true)

        println(ps.particles.size)
    }

}

