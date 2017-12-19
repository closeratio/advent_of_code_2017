package com.aoc2017.day18_part2

import com.aoc2017.day18_part2.instructions.Instruction
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

class ProgramHost {

    val prog0To1List = LinkedBlockingQueue<Long>()
    val prog1To0List = LinkedBlockingQueue<Long>()

    val prog0 = Program(0, prog0To1List, prog1To0List)
    val prog1 = Program(1, prog1To0List, prog0To1List)

    private val exec = Executors.newFixedThreadPool(3)

    fun executeInstructions(instructions: List<Instruction>) {
        exec.submit { prog0.executeAllInstructions(instructions) }
        exec.submit { prog1.executeAllInstructions(instructions) }

        checkForDeadlock()
    }

    private fun checkForDeadlock() {
        var sameCount = 0
        var prog1InstIndex = -1

        while (sameCount < 10) {
            if (prog1.currInstIndex == prog1InstIndex) {
                sameCount++
            } else {
                sameCount = 0
                prog1InstIndex = prog1.currInstIndex
            }
            Thread.sleep(100)
        }

        exec.shutdownNow()
    }

}