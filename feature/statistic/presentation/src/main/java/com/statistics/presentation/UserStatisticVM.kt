package com.statistics.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.statistics.domain.models.network.ErrorType
import com.statistics.domain.models.network.Result
import com.statistics.impl.GetStatisticDataUseCase
import com.statistics.presentation.models.Event
import com.statistics.presentation.models.ScreenState
import com.statistics.uikit.ErrorScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
            is Event.Refresh -> loadData()
            is Event.FilterVisitorsByTime -> {}
            is Event.FilterVisitorsByPeriod -> {}
        }
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getDataUseCase()
            }.onFailure {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorType = ErrorScreenState.NOTHING_FOUND
                    )
                }
            }.onSuccess { flow ->
                flow.collect { result ->
                    when (result) {
                        is Result.Error -> handleError(result)
                        is Result.Success -> {
                            val (users, events) = result.data
                            Log.i(TAG, "Loaded users: $users")
                            Log.i(TAG, "Loaded events: $events")

                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorType = null,
                                    users = users.toList(),
                                    events = events
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun handleError(result: Result.Error<*, ErrorType>) {
        val errorState = when (result.error) {
            ErrorType.NO_CONNECTION -> ErrorScreenState.NO_INTERNET
            ErrorType.SERVER_ERROR -> ErrorScreenState.SERVER_ERROR
            else -> ErrorScreenState.NOTHING_FOUND
        }

        _state.update {
            it.copy(
                isLoading = false,
                errorType = errorState
            )
        }
    }

    private companion object {
        val TAG = UserStatisticVM::class.simpleName
    }
}