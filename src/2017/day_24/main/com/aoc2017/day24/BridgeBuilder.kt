package com.aoc2017.day24

object BridgeBuilder {

    fun GeneratePermutations(components: List<Component>): List<Bridge> {
        return GenPermutationsRecursive(
                components,
                Bridge(listOf()),
                0)
    }

    private fun GenPermutationsRecursive(
            components: List<Component>,
            currBridge: Bridge,
            targetPort: Int): List<Bridge> {
        if (components.isEmpty()) {
            return listOf()
        }

        val availComps = components.filter { it.port1 == targetPort || it.port2 == targetPort }

        return availComps.map { comp ->
            val newBridge = Bridge(currBridge.components
                    .toMutableList()
                    .apply { add(comp) })

            val childBridges = GenPermutationsRecursive(
                    components.filter { it != comp },
                    newBridge,
                    if (comp.port1 == targetPort) comp.port2 else comp.port1)

            mutableListOf(newBridge).apply { addAll(childBridges) }
        }.flatMap { it }
    }

}

