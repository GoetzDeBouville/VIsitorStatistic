package com.statistics.data.impl.network

import android.content.Context
import com.statistics.core.data.network.client.HttpKtorNetworkClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.path
import javax.inject.Inject

class HttpStatisticKtorClient @Inject constructor(
    context: Context,
    private val httpClient: HttpClient
) : HttpKtorNetworkClient<StatisticRequest, StatisticResponse>(context) {
    override suspend fun sendRequestByType(request: StatisticRequest): HttpResponse {
        return when (request) {
            StatisticRequest.EventStatistic -> {
                httpClient.get {
                    url {
                        path(request.path)
                    }
                }
            }
            StatisticRequest.UserStatistic -> {
                httpClient.get {
                    url {
                        path(request.path)
                    }
                }
            }
        }
    }

    override suspend fun getResponseBodyByRequestType(
        requestType: StatisticRequest,
        httpResponse: HttpResponse
    ): StatisticResponse {
        return when (requestType) {
            StatisticRequest.EventStatistic -> StatisticResponse.EventList(httpResponse.body())
            StatisticRequest.UserStatistic -> StatisticResponse.UserList(httpResponse.body())
        }
    }
}