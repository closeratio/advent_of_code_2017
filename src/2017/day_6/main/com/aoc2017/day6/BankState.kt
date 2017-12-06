package com.aoc2017.day6

class BankState(vararg banks: Int) {

    val banks = banks

    override fun equals(other: Any?): Boolean {
        val o = other as? BankState ?: return false
        return banks.contentEquals(o.banks)
    }

    override fun hashCode(): Int {
        return banks.contentHashCode()
    }

    override fun toString(): String {
        return "${banks.toList()}"
    }

    fun clone(): BankState {
        return BankState(*banks)
    }
}