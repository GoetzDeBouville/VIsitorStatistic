package com.statistics.presentation.models

internal sealed interface Event {
    data object Refresh : Event
}