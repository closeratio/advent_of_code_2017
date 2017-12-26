package com.aoc2017.day25

import com.aoc2017.day25.BlueprintParser.ParseBlueprint
import org.testng.Assert
import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import org.testng.annotations.Test

class TestBlueprintParser {

    val TEST_DATA =
            "Begin in state A.\n" +
            "Perform a diagnostic checksum after 6 steps.\n" +
            "\n" +
            "In state A:\n" +
            "  If the current value is 0:\n" +
            "    - Write the value 1.\n" +
            "    - Move one slot to the right.\n" +
            "    - Continue with state B.\n" +
            "  If the current value is 1:\n" +
            "    - Write the value 0.\n" +
            "    - Move one slot to the left.\n" +
            "    - Continue with state B.\n" +
            "\n" +
            "In state B:\n" +
            "  If the current value is 0:\n" +
            "    - Write the value 1.\n" +
            "    - Move one slot to the left.\n" +
            "    - Continue with state A.\n" +
            "  If the current value is 1:\n" +
            "    - Write the value 1.\n" +
            "    - Move one slot to the right.\n" +
            "    - Continue with state A."

    @Test
    fun testParseInitialState() {
        assertEquals(ParseBlueprint(TEST_DATA).initialState, "A")
    }

    @Test
    fun testParseChecksumStep() {
        assertEquals(ParseBlueprint(TEST_DATA).diagChecksumStep, 6)
    }

    @Test
    fun testParseRuleset() {
        val bp = ParseBlueprint(TEST_DATA)

        val rs0 = bp.rulesets[0]
        assertEquals(rs0.targetState, "A")
        assertEquals(rs0.valueConditions[0].currValue, 0)

        val wa =  rs0.valueConditions[0].actions[0] as WriteAction
        assertEquals(wa.writeValue, 1)

        val ma =  rs0.valueConditions[0].actions[1] as MoveAction
        assertEquals(ma.direction, Direction.RIGHT)
        assertEquals(ma.moveAmount, 1)

        val csa = rs0.valueConditions[0].actions[2] as ChangeStateAction
        assertEquals(csa.newStateName, "B")

        assertEquals(rs0.valueConditions[1].currValue, 1)

        val rs1 = bp.rulesets[1]
        assertEquals(rs1.targetState, "B")
        assertEquals(rs0.valueConditions[0].currValue, 0)
    }

}