package com.statistics.impl

import com.statistics.domain.api.UserStatisticRepository
import com.statistics.domain.models.EventStatistic
import com.statistics.domain.models.User
import com.statistics.domain.models.network.ErrorType
import com.statistics.domain.models.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetStatisticDataUseCase @Inject constructor(
    private val userStatisticRepository: UserStatisticRepository
) {
    operator fun invoke(): Flow<Result<List<Pair<User, EventStatistic>>, ErrorType>> {
        return userStatisticRepository.getUsersStatistic().combine(
            userStatisticRepository.getEventStatistic()
        ) { userResult, eventResult ->

            when {
                userResult is Result.Error -> Result.Error(userResult.error)
                eventResult is Result.Error -> Result.Error(eventResult.error)
                userResult is Result.Success && eventResult is Result.Success -> {
                    val users = userResult.data
                    val events = eventResult.data

                    val result = users.mapNotNull { user ->
                        val statistic = events.find { it.userId == user.id }
                        statistic?.let { user to it }
                    }

                    Result.Success(result)
                }
                else -> Result.Error(ErrorType.UNKNOWN_ERROR)
            }
        }
    }
}