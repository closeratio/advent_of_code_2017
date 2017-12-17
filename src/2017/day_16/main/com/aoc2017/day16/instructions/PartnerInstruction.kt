package com.aoc2017.day16.instructions

import com.aoc2017.day16.ProgramSet

class PartnerInstruction(
        val nameA: String,
        val nameB: String
): Instruction() {

    override fun execute(ps: ProgramSet): List<String> {
        return ExchangeInstruction(
                getIndex(ps, nameA),
                getIndex(ps, nameB)
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

    private fun getIndex(ps: ProgramSet, name: String): Int {
        ps.programs.forEachIndexed { index, prog ->
            if (prog == name) {
                return index
            }
        }

        throw RuntimeException("Can't find prog with name: $name")
    }
}