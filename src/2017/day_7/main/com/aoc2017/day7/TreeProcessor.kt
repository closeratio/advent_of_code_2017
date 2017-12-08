package com.aoc2017.day7

object TreeProcessor {

    fun ParseDiscDef(line: String, discMap: MutableMap<String, Disc>): Disc {
        val result = Regex("(\\w+) \\((\\d+)\\)").find(line.trim())!!
        val disc = Disc(result.groups[1]!!.value, result.groups[2]!!.value.toInt())

        if (discMap.containsKey(disc.name)) {
            discMap[disc.name]!!.weight = disc.weight
        } else {
            discMap[disc.name] = disc
        }

        return discMap[disc.name]!!
    }

    fun ParseDiscChildren(line: String, parent: Disc, discMap: MutableMap<String, Disc>) {
        line.trim()
                .split(",")
                .map { it.trim() }
                .forEach {
                    if (discMap.contains(it)) {
                        parent.addChild(discMap[it]!!)
                    } else {
                        val child = Disc(it, 0)
                        discMap[it] = child
                        parent.addChild(child)
                    }
                }
    }

    fun ParseLine(line: String, discMap: MutableMap<String, Disc>): Disc {
        return if (line.contains("->")) {
            val split = line.split("->")
            val disc = ParseDiscDef(split[0].trim(), discMap)
            ParseDiscChildren(split[1].trim(), disc, discMap)
            disc
        } else {
            ParseDiscDef(line.trim(), discMap)
        }
    }

}

