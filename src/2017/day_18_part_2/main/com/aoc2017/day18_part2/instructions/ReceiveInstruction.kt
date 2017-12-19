package com.aoc2017.day18_part2.instructions

import com.aoc2017.day18_part2.Register

class ReceiveInstruction(
        val reg: Register)
    : Instruction() {

    override fun equals(other: Any?): Boolean {
        val inst = other as? ReceiveInstruction ?: return false
        return reg == inst.reg
    }

    override fun hashCode(): Int {
        return reg.hashCode()
    }

    override fun toString(): String {
        return "Recover instruction ($reg)"
    }

    override fun getSourceRep(): String {
        return "rcv ${reg.getSourceRep()}"
    }
}