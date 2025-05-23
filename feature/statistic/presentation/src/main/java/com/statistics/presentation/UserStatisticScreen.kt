package com.statistics.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun UserStatisticScreen(
    isWideDisplay: Boolean,
    navController: NavController,
    viewModel: UserStatisticVM = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
}