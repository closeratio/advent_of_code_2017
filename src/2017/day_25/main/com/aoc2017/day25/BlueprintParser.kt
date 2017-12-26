package com.aoc2017.day25

import java.io.IOException

object BlueprintParser {

    fun ParseBlueprint(data: String): Blueprint {
        return Blueprint(
                GetInitialState(data),
                GetChecksumStep(data),
                GetRulesets(data)
        )
    }

    private fun GetInitialState(data: String): String {
        val initialStateRegex = Regex("Begin in state (\\w+)")
        return initialStateRegex.find(data)!!.groupValues[1]
    }

    private fun GetChecksumStep(data: String): Int {
        val checksumRegex = Regex("checksum after (\\d+) steps")
        return checksumRegex.find(data)!!.groupValues[1].toInt()
    }

    private fun GetRulesets(data: String): List<Ruleset> {
        val spl = data.split("\n\n")
        return spl.subList(1, spl.size).map { GetRuleset(it.trim()) }
    }

    private fun GetRuleset(data: String): Ruleset {
        val targetStateRegex = Regex("In state (\\w+):")
        val valueConditionRegex = Regex("If the current value is \\d:(\\s+-[ a-zA-Z0-9.]+)+")

        return Ruleset(
                targetStateRegex.find(data)!!.groupValues[1],
                valueConditionRegex.findAll(data)
                        .map { ParseValueCondition(it.value) }
                        .toList())
    }

    private fun ParseValueCondition(data: String): ValueCondition {
        val condRegex = Regex("If the current value is (\\d+)")
        val actionRegex = Regex("- [ a-zA-Z0-9]+")
        return ValueCondition(
                condRegex.find(data)!!.groupValues[1].toInt(),
                actionRegex.findAll(data).map { ParseAction(it.value) }.toList()
        )
    }

    private fun ParseAction(data: String): Action {
        val writeRegex = Regex("Write the value (\\d+)")
        val moveRegex = Regex("Move one slot to the ([a-z]+)")
        val changeStateRegex = Regex("Continue with state ([A-Z]+)")

        return when {
            writeRegex.containsMatchIn(data) -> {
                WriteAction(writeRegex.find(data)!!.groupValues[1].toInt())
            }
            moveRegex.containsMatchIn(data) -> {
                MoveAction(1, when (moveRegex.find(data)!!.groupValues[1]) {
                    "right" -> Direction.RIGHT
                    "left" -> Direction.LEFT
                    else -> throw IOException("Unknown direction: $data")
                })
            }
            changeStateRegex.containsMatchIn(data) -> {
                ChangeStateAction(changeStateRegex.find(data)!!.groupValues[1])
            }
            else -> throw IOException("Bad action string: $data")
        }
    }

}

