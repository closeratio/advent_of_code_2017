package com.aoc2017.day11

class HexCell(
        val pos: Vec2 = Vec2()
) {

    fun adjacent(dir: Direction): HexCell {
        return when (dir) {
            Direction.NORTH -> HexCell(Vec2(pos.x, pos.y + 2))
            Direction.NORTH_EAST -> HexCell(Vec2(pos.x + 1, pos.y + 1))
            Direction.SOUTH_EAST -> HexCell(Vec2(pos.x + 1, pos.y - 1))
            Direction.SOUTH -> HexCell(Vec2(pos.x, pos.y - 2))
            Direction.SOUTH_WEST -> HexCell(Vec2(pos.x - 1, pos.y - 1))
            Direction.NORTH_WEST -> HexCell(Vec2(pos.x - 1, pos.y + 1))
            else -> throw RuntimeException("Unhandled direction: $dir")
        }
    }

    override fun equals(other: Any?): Boolean {
        val hc = other as? HexCell ?: return false
        return pos == hc.pos
    }

    override fun hashCode(): Int {
        return pos.hashCode()
    }

    override fun toString(): String {
        return "HexCell (${pos.x}, ${pos.y})"
    }
}

