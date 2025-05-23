package com.statistics.feature.statistic.di

import android.content.Context
import com.statistics.core.data.network.client.HttpNetworkClient
import com.statistics.data.impl.UserStatisticRepositoryImpl
import com.statistics.data.impl.network.HttpStatisticKtorClient
import com.statistics.data.impl.network.StatisticRequest
import com.statistics.data.impl.network.StatisticResponse
import com.statistics.domain.api.UserStatisticRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        client: HttpStatisticKtorClient
    ): HttpNetworkClient<StatisticRequest, StatisticResponse> {
        return client
    }

    @Provides
    @Singleton
    fun provideUserStatisticRepository(
        statisticClient: HttpNetworkClient<StatisticRequest, StatisticResponse>
    ): UserStatisticRepository {
        return UserStatisticRepositoryImpl(statisticClient)
    }


    @Provides
    @Singleton
    fun provideUserStatisticClient(
        @ApplicationContext context: Context,
        httpClient: HttpClient
    ): HttpStatisticKtorClient {
        return HttpStatisticKtorClient(context, httpClient)
    }

}