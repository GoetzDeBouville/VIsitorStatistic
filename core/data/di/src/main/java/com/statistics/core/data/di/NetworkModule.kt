package com.statistics.core.data.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://test.rikmasters.ru/"
    private const val CONNECTION_TIME_OUT_10_SEC = 10_000L
    private const val MAX_REQUEST_DELAY_10_SEC = 10_000L
    private const val MAX_RETRIES_NUM_3 = 3

    @Provides
    fun provideNetworkClient(): HttpClient = HttpClient {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Logger Ktor", "=> $message")
                }
            }
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            requestTimeoutMillis = CONNECTION_TIME_OUT_10_SEC
            connectTimeoutMillis = CONNECTION_TIME_OUT_10_SEC
        }

        install(HttpRequestRetry) {
            retryOnServerErrors(MAX_RETRIES_NUM_3)
            retryOnException(maxRetries = MAX_RETRIES_NUM_3)
            exponentialDelay(maxDelayMs = MAX_REQUEST_DELAY_10_SEC)
            modifyRequest { request ->
                request.headers.append("x-retry-count", "2")
            }
        }

        install(ContentNegotiation) {
            json(
                json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.v("Logger Ktor", "HTTP status: ${response.status.value}")
            }
        }

        defaultRequest {
            url(BASE_URL)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}