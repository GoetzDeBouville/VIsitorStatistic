package com.statistics.core.presentation.nav

sealed class Routes(val route: String) {
    data object UserStatistic : Routes("user_statistic")
}