package com.statistics.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.statistics.impl.GetStatisticDataUseCase
import com.statistics.presentation.models.Event
import com.statistics.presentation.models.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserStatisticVM @Inject constructor(
    private val getDataUseCase: GetStatisticDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ScreenState(isLoading = true))
    internal val state = _state.asStateFlow()

    init {
        loadData()
    }

    internal fun accept(event: Event) {
        when (event) {
            Event.Refresh -> loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getDataUseCase()
            }.onFailure { e ->
                Log.e(TAG, e.message.toString())
            }.onSuccess { response ->
                response.collect { result ->
                    Log.i(TAG, result.toString())
                }
            }
        }
    }

    private companion object {
        val TAG = UserStatisticVM::class.simpleName
    }
}