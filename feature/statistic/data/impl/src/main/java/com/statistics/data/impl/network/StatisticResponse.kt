package com.statistics.data.impl.network

import com.statistics.data.impl.dto.StatisticsResponseDto
import com.statistics.data.impl.dto.UsersResponseDto

sealed interface StatisticResponse {
    class EventList(val value: StatisticsResponseDto) : StatisticResponse

    class UserList(val value: UsersResponseDto) : StatisticResponse
}