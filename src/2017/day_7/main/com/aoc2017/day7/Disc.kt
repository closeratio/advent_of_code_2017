package com.aoc2017.day7

class Disc(
        val name: String,
        var weight: Int,
        val children: MutableList<Disc> = ArrayList(),
        var parent: Disc? = null) {

    fun addChild(disc: Disc) {
        children.add(disc)
        disc.parent = this
    }

    fun addChildren(vararg discs: Disc) {
        discs.forEach { addChild(it) }
    }

    override fun equals(other: Any?): Boolean {
        val disc = other as? Disc ?: return false
        return disc.name == name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "Disc $name ($weight) ${children.map { it.toString() }}"
    }

    fun calculateWeight(): Int {
        return weight + children.map { it.calculateWeight() }.sum()
    }

    fun findPrefWeight(): Int {
        val weightCountMap = HashMap<Int, Int>()
        children.forEach {
            val w = it.calculateWeight()
            weightCountMap[w] = (weightCountMap[w] ?: 0) + 1
        }

        return weightCountMap
                .filter { it.value > 1 }
                .map { it.key }
                .first()
    }

    fun getUnbalancedChild(): Disc? {
        val weightCountMap = HashMap<Int, MutableList<Disc>>()
        children.forEach {
            val w = it.calculateWeight()
            weightCountMap
                    .getOrPut(w, { ArrayList() })
                    .add(it)
        }

        return weightCountMap
                .filter { it.value.size == 1 }
                .map { it.value[0] }
                .firstOrNull()
    }

    fun calculateRequiredWeight(prefWeight: Int = -1): Int {
        return if (prefWeight == -1) {
            getUnbalancedChild()!!.calculateRequiredWeight(findPrefWeight())
        } else {
            val unbal = getUnbalancedChild()

            if (unbal != null) {
                unbal.calculateRequiredWeight(findPrefWeight())
            } else {
                val childWeight = children.map { it.calculateWeight() }.sum()
                prefWeight - childWeight
            }

        }
    }
}