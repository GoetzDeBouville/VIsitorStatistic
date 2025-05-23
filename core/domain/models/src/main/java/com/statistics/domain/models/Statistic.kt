package com.statistics.domain.models

data class Statistic(
    val dates: List<Int>,
    val type: String,
    val userId: Int
)