package com.aoc2017.day18.instructions

import com.aoc2017.day18.Register

class RecoverInstruction(
        val reg: Register)
    : Instruction() {

    override fun equals(other: Any?): Boolean {
        val inst = other as? RecoverInstruction ?: return false
        return reg == inst.reg
    }

    override fun hashCode(): Int {
        return reg.hashCode()
    }

    override fun toString(): String {
        return "Recover instruction ($reg)"
    }
}