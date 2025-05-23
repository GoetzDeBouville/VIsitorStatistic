package com.statistics.presentation

import androidx.lifecycle.ViewModel
import com.statistics.domain.api.UserStatisticRepository
import com.statistics.presentation.models.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UserStatisticVM @Inject constructor(
    private val repository: UserStatisticRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ScreenState(isLoading = true))
    internal val state = _state.asStateFlow()
}