package com.statistics.data.impl.network

sealed class StatisticRequest(val path: String) {

    data object EventStatistic : StatisticRequest(path = "api/statistics/")

    data object UserStatistic : StatisticRequest(path = "api/users/")
}