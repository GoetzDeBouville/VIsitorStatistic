package com.statistics.domain.api

import com.statistics.domain.models.EventStatistic
import com.statistics.domain.models.User
import com.statistics.domain.models.network.ErrorType
import com.statistics.domain.models.network.Result
import kotlinx.coroutines.flow.Flow

interface UserStatisticRepository {
    fun getUsersStatistic(): Flow<Result<List<User>, ErrorType>>
    fun getEventStatistic(): Flow<Result<List<EventStatistic>, ErrorType>>
}