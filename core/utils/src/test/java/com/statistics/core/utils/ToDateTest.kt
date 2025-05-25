package com.statistics.core.utils

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate

class ToDateTest {
    @Test
    fun `valid dates with single digit day and month`() {
        assertEquals(LocalDate.of(2024, 9, 1), 1092024.toDate())
        assertEquals(LocalDate.of(2024, 9, 3), 3092024.toDate())
        assertEquals(LocalDate.of(2024, 9, 9), 9092024.toDate())
        assertEquals(LocalDate.of(2024, 9, 5), 5092024.toDate())
        assertEquals(LocalDate.of(2024, 9, 4), 4092024.toDate())
        assertEquals(LocalDate.of(2024, 9, 6), 6092024.toDate())
    }

    @Test
    fun `valid date with double digit day and month`() {
        assertEquals(LocalDate.of(2024, 10, 10), 10102024.toDate())
        assertEquals(LocalDate.of(2024, 12, 31), 31122024.toDate())
        assertEquals(LocalDate.of(2024, 9, 10), 10092024.toDate())
        assertEquals(LocalDate.of(2024, 9, 20), 20092024.toDate())
    }

    @Test
    fun `invalid date - too short`() {
        assertNull(924.toDate())
    }

    @Test
    fun `invalid date - too long`() {
        assertNull(110120244.toDate())
    }

    @Test
    fun `invalid date - malformed day and month`() {
        assertNull(13202024.toDate())
        assertNull(32022024.toDate())
    }
}