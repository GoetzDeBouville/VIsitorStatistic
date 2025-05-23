package com.statistics.presentation.models

import com.statistics.domain.models.EventStatistic
import com.statistics.domain.models.User
import com.statistics.uikit.ErrorScreenState

internal data class ScreenState(
    val itemList: List<Pair<User, EventStatistic>> = emptyList(),
    val isLoading: Boolean = false,
    val errorType: ErrorScreenState? = null
)