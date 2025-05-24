package com.statistics.feature.statistic.domain.di

import com.statistics.domain.api.UserStatisticRepository
import com.statistics.impl.GetStatisticDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class StatisticDomainModule {
    @Provides
    fun provideUseCase(repository: UserStatisticRepository): GetStatisticDataUseCase {
        return GetStatisticDataUseCase(repository)
    }
}