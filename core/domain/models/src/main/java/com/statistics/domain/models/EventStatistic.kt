package com.statistics.domain.models

data class EventStatistic(
    val dates: List<Int>,
    val type: String,
    val userId: Int
)