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
    operator fun invoke(): Flow<Result<Pair<Set<User>, List<EventStatistic>>, ErrorType>> {
        return userStatisticRepository.getUsersStatistic().combine(
            userStatisticRepository.getEventStatistic()
        ) { userResult, eventResult ->

            when {
                userResult is Result.Error -> Result.Error(userResult.error)
                eventResult is Result.Error -> Result.Error(eventResult.error)
                userResult is Result.Success && eventResult is Result.Success -> {
                    val users = userResult.data
                    val events = eventResult.data

                    val filteredEvents = events.filter { event ->
                        users.any { user -> user.id == event.userId }
                    }

                    val usersWithEvents = users.filter { user ->
                        filteredEvents.any { event -> event.userId == user.id }
                    }.toSet()

                    Result.Success(usersWithEvents to filteredEvents)
                }

                else -> Result.Error(ErrorType.UNKNOWN_ERROR)
            }
        }
    }

    private companion object {
        val TAG = GetStatisticDataUseCase::class.simpleName
    }
}
