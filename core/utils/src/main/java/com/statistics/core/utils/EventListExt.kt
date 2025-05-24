package com.statistics.core.utils

import com.statistics.domain.models.EventCounter
import com.statistics.domain.models.EventStatistic
import java.time.LocalDate

@Suppress("CognitiveComplexMethod", "NestedBlockDepth")
fun List<EventStatistic>.visitorsProgress(now: LocalDate): EventCounter {
    val dayLastMonth = now.minusMonths(1)

    var visitorsCounterCurrentMonth = 0
    var visitorsCounterPrevMonth = 0
    var subscribersCounterCurrentMonth = 0
    var subscribersCounterPrevMonth = 0

    for (event in this) {
        for (dateInt in event.dates) {
            val eventDate = dateInt.toString().toDate() ?: continue
            val isCurrentMonth = eventDate >= dayLastMonth

            when (event.type) {
                "view" -> {
                    if (isCurrentMonth) {
                        visitorsCounterCurrentMonth++
                    } else {
                        visitorsCounterPrevMonth++
                    }
                }
                "subscription" -> {
                    if (isCurrentMonth) {
                        subscribersCounterCurrentMonth++
                    } else {
                        subscribersCounterPrevMonth++
                    }
                }
            }
        }
    }

    return EventCounter(
        visitorsCounterCurrentMonth,
        visitorsCounterPrevMonth,
        subscribersCounterCurrentMonth,
        subscribersCounterPrevMonth
    )
}
