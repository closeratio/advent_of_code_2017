package com.aoc2017.day25

class Machine(
        val blueprint: Blueprint
) {

    var cursorIndex = 0
    var state = blueprint.initialState
    val tapeMap = hashMapOf(0 to 0)

    val ruleMap = blueprint.rulesets.associateBy({
        it.targetState
    }, {
        it.valueConditions
    })

    fun executeUntilChecksum(): Int {
        repeat(blueprint.diagChecksumStep) {
            execute()
        }

        return tapeMap.values.filter { it == 1 }.count()
    }

    fun execute() {
        val valueConditions = ruleMap[state] ?: throw RuntimeException("No rules for state $state")
        val currValue = getTapeValue(cursorIndex)

        val actions = valueConditions
                .filter { it.currValue == currValue }
                .first()
                .actions
                .forEach {
                    when (it) {
                        is WriteAction -> performWriteAction(it)
                        is MoveAction -> performMoveAction(it)
                        is ChangeStateAction -> performChangeStateAction(it)
                        else -> throw RuntimeException("Unhandled action: $it")
                    }
                }
    }

    private fun performWriteAction(writeAction: WriteAction) {
        tapeMap[cursorIndex] = writeAction.writeValue
    }

    private fun performMoveAction(moveAction: MoveAction) {
        cursorIndex += when (moveAction.direction) {
            Direction.LEFT -> -moveAction.moveAmount
            Direction.RIGHT -> moveAction.moveAmount
            else -> throw RuntimeException("Unhandled direction: ${moveAction.direction}")
        }
    }

    private fun performChangeStateAction(changeStateAction: ChangeStateAction) {
        state = changeStateAction.newStateName
    }

    fun getTapeValue(index: Int): Int {
        return tapeMap.getOrPut(index, { 0 })
    }

}