package com.statistics.core.data.network.client

import android.content.Context
import android.util.Log
import com.statistics.core.data.network.models.Response
import com.statistics.core.data.network.models.StatusCode
import com.statistics.core.utils.isInternetReachable
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException

abstract class HttpKtorNetworkClient<SealedRequest, SealedResponse>(
    private val context: Context
) : HttpNetworkClient<SealedRequest, SealedResponse> {
    override suspend fun getResponse(sealedRequest: SealedRequest): Response<SealedResponse> {
        return if (context.isInternetReachable()) {
            runCatching {
                mapToResponse(
                    requestType = sealedRequest,
                    httpResponse = sendRequestByType(sealedRequest)
                )
            }.onFailure { error ->
                Log.v(TAG, "error -> ${error.localizedMessage}")
                error.printStackTrace()
            }.getOrNull() ?: Response()
        } else {
            Response(isSuccess = false, resultCode = StatusCode(-1))
        }
    }

    protected abstract suspend fun sendRequestByType(request: SealedRequest): HttpResponse

    protected abstract suspend fun getResponseBodyByRequestType(
        requestType: SealedRequest,
        httpResponse: HttpResponse
    ): SealedResponse

    private suspend fun mapToResponse(
        requestType: SealedRequest,
        httpResponse: HttpResponse
    ): Response<SealedResponse> {
        Log.v(TAG, "HTTP response status: ${httpResponse.status.value}")
        Log.v(TAG, "body = ${httpResponse.bodyAsText()}")
        return if (httpResponse.status.isSuccess()) {
            try {
                Response(
                    isSuccess = true,
                    resultCode = StatusCode(httpResponse.status.value),
                    body = getResponseBodyByRequestType(requestType, httpResponse)
                )
            } catch (e: SerializationException) {
                Log.v(TAG, e.message.toString())
                Response(
                    isSuccess = false,
                    resultCode = StatusCode(httpResponse.status.value)
                )
            } catch (e: IOException) {
                Log.v(TAG, e.message.toString())
                Response(
                    isSuccess = false,
                    resultCode = StatusCode(httpResponse.status.value)
                )
            }
        } else {
            Response(
                isSuccess = false,
                resultCode = StatusCode(httpResponse.status.value)
            )
        }
    }

    private companion object {
        val TAG = HttpKtorNetworkClient::class.simpleName ?: "HttpKtorNetworkClient"
    }
}
