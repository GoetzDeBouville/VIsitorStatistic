package com.statistics.data.impl.network

import com.statistics.core.data.dto.StatisticDto
import com.statistics.core.data.dto.UserDto

sealed interface StatisticResponse {
    class EventList(val value: List<StatisticDto>) : StatisticResponse

    class UserList(val value: List<UserDto>) : StatisticResponse
}