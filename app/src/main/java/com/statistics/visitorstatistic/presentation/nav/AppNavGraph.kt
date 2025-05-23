package com.statistics.visitorstatistic.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.statistics.core.presentation.nav.Routes
import com.statistics.presentation.UserStatisticScreen


@Composable
fun AppNavGraph(isWideDisplay: Boolean) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.UserStatistic.route) {
        composable(Routes.UserStatistic.route) {
            UserStatisticScreen(
                isWideDisplay = isWideDisplay,
                navController = navController
            )
        }
    }
}