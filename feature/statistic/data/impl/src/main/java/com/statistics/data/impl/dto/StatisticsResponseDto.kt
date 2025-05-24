package com.statistics.data.impl.dto

import com.statistics.core.data.dto.StatisticDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatisticsResponseDto(
    @SerialName("statistics")
    val statistics: List<StatisticDto>
)