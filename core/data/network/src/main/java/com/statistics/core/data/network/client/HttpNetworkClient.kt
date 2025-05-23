package com.statistics.core.data.network.client

import com.statistics.core.data.network.models.Response

interface HttpNetworkClient<T, R> {
    suspend fun getResponse(sealedRequest: T): Response<R>
}