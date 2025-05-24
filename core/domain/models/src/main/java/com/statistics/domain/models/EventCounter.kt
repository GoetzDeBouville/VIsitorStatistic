package com.statistics.domain.models

data class EventCounter(
    val visitorsCounterCurrentMonth: Int,
    val visitorsCounterPrevMonth: Int,
    val subscribersCounterCurrentMonth: Int,
    val subscribersCounterPrevMonth: Int
)
