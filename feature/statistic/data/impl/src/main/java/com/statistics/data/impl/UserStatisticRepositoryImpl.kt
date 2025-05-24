package com.statistics.data.impl

import android.util.Log
import com.statistics.core.data.dto.mappers.toDomain
import com.statistics.core.data.network.client.HttpNetworkClient
import com.statistics.core.data.network.models.mapToErrorType
import com.statistics.data.impl.network.StatisticRequest
import com.statistics.data.impl.network.StatisticResponse
import com.statistics.domain.api.UserStatisticRepository
import com.statistics.domain.models.EventStatistic
import com.statistics.domain.models.User
import com.statistics.domain.models.network.ErrorType
import com.statistics.domain.models.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserStatisticRepositoryImpl @Inject constructor(
    private val httpStatisticKtorClient: HttpNetworkClient<StatisticRequest, StatisticResponse>
) : UserStatisticRepository {
    override fun getUsersStatistic(): Flow<Result<List<User>, ErrorType>> = flow {
        val response = httpStatisticKtorClient.getResponse(StatisticRequest.UserStatistic)
        when (val body = response.body) {
            is StatisticResponse.UserList -> {
                emit(Result.Success(body.value.users.map { it.toDomain() }))
            }

            else -> {
                Log.i(TAG, "getUsersStatistic = ${response.body}")
                emit(Result.Error(response.resultCode.mapToErrorType()))
            }
        }
    }

    override fun getEventStatistic(): Flow<Result<List<EventStatistic>, ErrorType>> = flow {
        val response = httpStatisticKtorClient.getResponse(StatisticRequest.EventStatistic)
        Log.i(TAG, "getEventStatistic = ${response.body}")
        when (val body = response.body) {
            is StatisticResponse.EventList -> {
                emit(Result.Success(body.value.statistics.map { it.toDomain() }))
            }

            else -> {
                emit(Result.Error(response.resultCode.mapToErrorType()))
            }
        }
    }

    private companion object {
        val TAG = UserStatisticRepositoryImpl::class.simpleName
    }
}