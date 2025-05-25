package com.statistics.core.utils

import com.statistics.domain.models.EventCounter
import com.statistics.domain.models.EventStatistic
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDate

class VisitorsProgressTest {

    private val referenceDate = LocalDate.of(2024, 9, 1)

    @Test
    fun `empty list returns all zeros`() {
        val result = emptyList<EventStatistic>().visitorsProgress(referenceDate)
        assertEquals(EventCounter(0, 0, 0, 0), result)
    }

    @Test
    fun `only current month views`() {
        val data = listOf(
            EventStatistic(
                dates = listOf(1092024, 15092024), // 01.09.2024, 15.09.2024
                type = "view",
                userId = 1
            )
        )
        val result = data.visitorsProgress(referenceDate)
        assertEquals(EventCounter(2, 0, 0, 0), result)
    }

    @Test
    fun `only previous month views`() {
        val data = listOf(
            EventStatistic(
                dates = listOf(1082024, 31082024), // 01.08.2024, 31.08.2024
                type = "view",
                userId = 1
            )
        )
        val result = data.visitorsProgress(referenceDate)
        assertEquals(EventCounter(0, 2, 0, 0), result)
    }

    @Test
    fun `views in both months`() {
        val data = listOf(
            EventStatistic(
                dates = listOf(1082024, 1092024), // 01.08.2024, 01.09.2024
                type = "view",
                userId = 1
            )
        )
        val result = data.visitorsProgress(referenceDate)
        assertEquals(EventCounter(1, 1, 0, 0), result)
    }

    @Test
    fun `subscriptions in both months`() {
        val data = listOf(
            EventStatistic(dates = listOf(1092024), type = "subscription", userId = 1), // 01.09.2024
            EventStatistic(dates = listOf(15092024), type = "unsubscription", userId = 1) // 15.09.2024
        )
        val result = data.visitorsProgress(referenceDate)
        assertEquals(EventCounter(0, 0, 1, 1), result)
    }

    @Test
    fun `mixed types in mixed months`() {
        val data = listOf(
            EventStatistic(dates = listOf(1092024), type = "view", userId = 1), // 01.09.2024
            EventStatistic(dates = listOf(1082024), type = "view", userId = 1), // 01.08.2024
            EventStatistic(dates = listOf(1092024), type = "subscription", userId = 2), // 01.09.2024
            EventStatistic(dates = listOf(1082024), type = "subscription", userId = 2), // 01.08.2024 — игнорируется
            EventStatistic(dates = listOf(15092024), type = "unsubscription", userId = 3) // 15.09.2024
        )
        val result = data.visitorsProgress(referenceDate)
        assertEquals(EventCounter(1, 1, 1, 1), result)
    }

    @Test
    fun `invalid dates are ignored`() {
        val data = listOf(
            EventStatistic(dates = listOf(99999999), type = "view", userId = 1) // некорректная дата
        )
        val result = data.visitorsProgress(referenceDate)
        assertEquals(EventCounter(0, 0, 0, 0), result)
    }
}