package com.aoc2017.day16.instructions

import com.aoc2017.day16.ProgramSet

abstract class Instruction {

    abstract fun execute(ps: ProgramSet): List<String>

}