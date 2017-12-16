package com.aoc2017.day14

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class TestDiskDefragmenter {

    @Test
    fun testConvertCharToBits() {
        assertEquals(DiskDefragmenter.convertCharToBits('a'), arrayOf(true, false, true, false))
        assertEquals(DiskDefragmenter.convertCharToBits('0'), arrayOf(false, false, false, false))
        assertEquals(DiskDefragmenter.convertCharToBits('c'), arrayOf(true, true, false, false))

        assertEquals(DiskDefragmenter.convertCharToBits('2'), arrayOf(false, false, true, false))
        assertEquals(DiskDefragmenter.convertCharToBits('0'), arrayOf(false, false, false, false))
        assertEquals(DiskDefragmenter.convertCharToBits('1'), arrayOf(false, false, false, true))
        assertEquals(DiskDefragmenter.convertCharToBits('7'), arrayOf(false, true, true, true))
    }

    @Test
    fun testDiskState() {
        val ds = DiskDefragmenter.getDiskState("flqrgnkx").state

        assertEquals(ds[0][0], true)
        assertEquals(ds[0][1], true)
        assertEquals(ds[0][2], false)
        assertEquals(ds[0][3], true)
        assertEquals(ds[0][4], false)
        assertEquals(ds[0][5], true)
        assertEquals(ds[0][6], false)
        assertEquals(ds[0][7], false)

        assertEquals(ds[1][0], false)
        assertEquals(ds[1][1], true)
        assertEquals(ds[1][2], false)
        assertEquals(ds[1][3], true)
        assertEquals(ds[1][4], false)
        assertEquals(ds[1][5], true)
        assertEquals(ds[1][6], false)
        assertEquals(ds[1][7], true)
    }

    @Test
    fun testUsedCount() {
        val ds = DiskDefragmenter.getDiskState("flqrgnkx")
        assertEquals(ds.getUsedCount(), 8108)
    }

    @Test
    fun testActualUsedCount() {
        println(DiskDefragmenter.getDiskState("uugsqrei").getUsedCount())
    }

    @Test
    fun testRegionCount() {
        assertEquals(DiskDefragmenter.getDiskState("flqrgnkx").getRegionCount(), 1242)
    }



}