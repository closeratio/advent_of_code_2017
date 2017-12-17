package com.aoc2017.day16.instructions

import com.aoc2017.day16.ProgramSet

class PartnerInstruction(
        val nameA: Char,
        val nameB: Char
): Instruction() {

    override fun execute(ps: ProgramSet): String {
        return ExchangeInstruction(
                ps.programs.indexOf(nameA),
                ps.programs.indexOf(nameB)
        ).execute(ps)
    }

    override fun equals(other: Any?): Boolean {
        val inst = other as? PartnerInstruction ?: return false
        return nameA == inst.nameA && nameB == inst.nameB
    }

    override fun hashCode(): Int {
        return nameA.hashCode() * nameB.hashCode()
    }

    override fun toString(): String {
        return "Partner instruction($nameA, $nameB)"
    }
}