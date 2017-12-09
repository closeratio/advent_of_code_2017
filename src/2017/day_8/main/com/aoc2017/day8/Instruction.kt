package com.aoc2017.day8

class Instruction(
        val targRegister: String,
        val oper: Operation,
        val operVal: Int,
        val condRegister: String,
        val cond: Condition,
        val condVal: Int) {

    override fun equals(other: Any?): Boolean {
        val inst = other as? Instruction ?: return false
        return targRegister == inst.targRegister &&
                oper == inst.oper &&
                operVal == inst.operVal &&
                condRegister == inst.condRegister &&
                cond == inst.cond &&
                condVal == inst.condVal
    }

    override fun hashCode(): Int {
        return targRegister.hashCode() *
                oper.hashCode() *
                operVal.hashCode() *
                condRegister.hashCode() *
                cond.hashCode() *
                condVal.hashCode()
    }

    override fun toString(): String {
        return "$targRegister $oper $operVal $condRegister $cond $condVal"
    }
}