package com.statistics.core.utils

import com.statistics.domain.models.EventCounter
import com.statistics.domain.models.EventStatistic
import java.time.LocalDate

@Suppress("CognitiveComplexMethod", "NestedBlockDepth", "t", "CyclomaticComplexMethod")
fun List<EventStatistic>.visitorsProgress(now: LocalDate): EventCounter {
    val startOfCurrentMonth = now.withDayOfMonth(1)
    val startOfPreviousMonth = startOfCurrentMonth.minusMonths(1)

    var visitorsCounterCurrentMonth = 0
    var visitorsCounterPrevMonth = 0
    var subscribersCounterCurrentMonth = 0
    var unsubscribersCounterCurrentMonth = 0

    for (event in this) {
        for (dateInt in event.dates) {
            val eventDate = dateInt.toDate() ?: continue
            val isCurrentMonth = eventDate >= startOfCurrentMonth && eventDate < startOfCurrentMonth.plusMonths(1)
            val isPreviousMonth = eventDate >= startOfPreviousMonth && eventDate < startOfCurrentMonth

            when (event.type) {
                "view" -> {
                    if (isCurrentMonth) {
                        visitorsCounterCurrentMonth++
                    } else if (isPreviousMonth) {
                        visitorsCounterPrevMonth++
                    }
                }
                "subscription" -> {
                    if (isCurrentMonth) subscribersCounterCurrentMonth++
                }
                "unsubscription" -> {
                    if (isCurrentMonth) unsubscribersCounterCurrentMonth++
                }
                else -> {}
            }
        }
    }

    return EventCounter(
        visitorsCounterCurrentMonth,
        visitorsCounterPrevMonth,
        subscribersCounterCurrentMonth,
        unsubscribersCounterCurrentMonth
    )
}

fun List<EventStatistic>.filterVewEventList(): List<EventStatistic> {
    return this.filter {
        it.type == "view"
    }
}