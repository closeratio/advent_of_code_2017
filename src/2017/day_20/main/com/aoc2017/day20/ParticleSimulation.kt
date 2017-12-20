package com.aoc2017.day20

import java.io.IOException

class ParticleSimulation(
        val input: String
) {

    private val VEC3_REGEX = Regex("(-?\\d+),(-?\\d+),(-?\\d+)")
    private val PARTICLE_REGEX = Regex("^p=<(.+)>, v=<(.+)>, a=<(.+)>")

    val particles: MutableList<Particle>
    val particleCount: Int

    val posMap = hashMapOf<Vec3, MutableList<Particle>>()

    init {
        particles = createParticles()
        particleCount = particles.size

        particles.forEach {
            posMap.getOrPut(it.pos, { ArrayList() }).add(it)
        }
    }

    private fun createParticles(): MutableList<Particle> {
        return input
                .trim()
                .split("\n")
                .map(this::parseParticle)
                .toMutableList()
    }

    private fun parseParticle(line: String): Particle {
        val result = PARTICLE_REGEX.matchEntire(line) ?: throw IOException("Bad particle definition: $line")

        val posGroup = result.groupValues[1]
        val pos = VEC3_REGEX.matchEntire(posGroup) ?: throw IOException("Bad def for position: $posGroup")

        val velGroup = result.groupValues[2]
        val vel = VEC3_REGEX.matchEntire(velGroup) ?: throw IOException("Bad def for velocity: $velGroup")

        val accGroup = result.groupValues[3]
        val acc = VEC3_REGEX.matchEntire(accGroup) ?: throw IOException("Bad def for acceleration: $accGroup")

        return Particle(
                Vec3(
                        pos.groupValues[1].toLong(),
                        pos.groupValues[2].toLong(),
                        pos.groupValues[3].toLong()),
                Vec3(
                        vel.groupValues[1].toLong(),
                        vel.groupValues[2].toLong(),
                        vel.groupValues[3].toLong()
                ),
                Vec3(
                        acc.groupValues[1].toLong(),
                        acc.groupValues[2].toLong(),
                        acc.groupValues[3].toLong()))
    }

    fun iterate(
            iterCount: Int = 1,
            handleCollisions: Boolean = false) {

        repeat(iterCount) {
            particles.forEach {
                it.vel += it.acc

                if (handleCollisions) {
                    posMap.getOrPut(it.pos, { ArrayList() }).remove(it)
                }

                it.pos += it.vel

                if (handleCollisions) {
                    posMap.getOrPut(it.pos, { ArrayList() }).add(it)
                }
            }

            if (handleCollisions) {
                handleCollisions()
                posMap.filter { it.value.isEmpty() }.forEach { posMap.remove(it.key) }
            }
        }
    }

    fun handleCollisions() {
        posMap.filter { it.value.size > 1 }
                .forEach { _, pList ->
                    pList.forEach { particles.remove(it) }
                    pList.clear()
                }
    }

    fun getClosestParticleToZero(): Int {
        return particles
                .mapIndexed { ind, p -> Pair(ind, p) }
                .sortedBy { it.second.pos.manhattanLength() }
                .map { it.first }
                .first()
    }

}

