package com.aoc2017.day5

import org.testng.Assert
import org.testng.Assert.*
import org.testng.annotations.Test
import java.io.File

class TestJumpProcessor {

    val INSTR_SET = "0\n3\n0\n1\n-3"
    val EXPEC_INSTR_SET = arrayOf(0, 3, 0, 1, -3)

    @Test
    fun testParseJumpSet1() {
        assertEquals(JumpProcessor.ParseJumpSet(INSTR_SET), EXPEC_INSTR_SET)
    }

    @Test
    fun testParseJumpSet2() {
        assertEquals(JumpProcessor.ParseJumpSet("3\n2\n8\n-3\n1"), arrayOf(3, 2, 8, -3, 1))
    }

    @Test
    fun testProgramState() {
        val ps = ProgramState(JumpProcessor.ParseJumpSet(INSTR_SET))
        val testps = ProgramState(ps.jumpSet.copyOf(), 0)

        assertEquals(ps.jumpSet, EXPEC_INSTR_SET)

        JumpProcessor.Execute(ps)

        testps.jumpSet[0] = 1
        testps.currIndex = 0
        testps.iterationCount++
        assertEquals(ps, testps)
        assertFalse(JumpProcessor.HasFinished(ps))

        JumpProcessor.Execute(ps)

        testps.jumpSet[0] = 2
        testps.currIndex = 1
        testps.iterationCount++
        assertEquals(ps, testps)
        assertFalse(JumpProcessor.HasFinished(ps))

        JumpProcessor.Execute(ps)

        testps.jumpSet[1] = 4
        testps.currIndex = 4
        testps.iterationCount++
        assertEquals(ps, testps)
        assertFalse(JumpProcessor.HasFinished(ps))

        JumpProcessor.Execute(ps)

        testps.jumpSet[4] = -2
        testps.currIndex = 1
        testps.iterationCount++
        assertEquals(ps, testps)
        assertFalse(JumpProcessor.HasFinished(ps))

        JumpProcessor.Execute(ps)

        testps.jumpSet[1] = 5
        testps.currIndex = 5
        testps.iterationCount++
        assertEquals(ps, testps)

        assertTrue(JumpProcessor.HasFinished(ps))

    }

    @Test
    fun testActualJumpSet() {
        val ps = ProgramState(
                JumpProcessor.ParseJumpSet(
                        File(javaClass.getResource("/input.txt").toURI()).readText()))

        while (!JumpProcessor.HasFinished(ps)) {
            JumpProcessor.Execute(ps)
        }

        println(ps.iterationCount)
    }

    @Test
    fun testAdvancedState() {
        val ps = ProgramState(JumpProcessor.ParseJumpSet(INSTR_SET))
        val testps = ProgramState(ps.jumpSet.copyOf(), 0)

        assertEquals(ps.jumpSet, EXPEC_INSTR_SET)

        JumpProcessor.ExecuteAdvanced(ps)

        testps.jumpSet[0] = 1
        testps.currIndex = 0
        testps.iterationCount++
        assertEquals(ps, testps)
        assertFalse(JumpProcessor.HasFinished(ps))

        JumpProcessor.ExecuteAdvanced(ps)

        testps.jumpSet[0] = 2
        testps.currIndex = 1
        testps.iterationCount++
        assertEquals(ps, testps)
        assertFalse(JumpProcessor.HasFinished(ps))

        JumpProcessor.ExecuteAdvanced(ps)

        testps.jumpSet[1] = 2
        testps.currIndex = 4
        testps.iterationCount++
        assertEquals(ps, testps)
        assertFalse(JumpProcessor.HasFinished(ps))

        JumpProcessor.ExecuteAdvanced(ps)

        testps.jumpSet[4] = -2
        testps.currIndex = 1
        testps.iterationCount++
        assertEquals(ps, testps)
        assertFalse(JumpProcessor.HasFinished(ps))

        JumpProcessor.ExecuteAdvanced(ps)

        testps.jumpSet[1] = 3
        testps.currIndex = 3
        testps.iterationCount++
        assertEquals(ps, testps)

        assertFalse(JumpProcessor.HasFinished(ps))

    }

    @Test
    fun testActualAdvancedJumpSet() {
        val ps = ProgramState(
                JumpProcessor.ParseJumpSet(
                        File(javaClass.getResource("/input.txt").toURI()).readText()))

        while (!JumpProcessor.HasFinished(ps)) {
            JumpProcessor.ExecuteAdvanced(ps)
        }

        println(ps.iterationCount)
    }

}

