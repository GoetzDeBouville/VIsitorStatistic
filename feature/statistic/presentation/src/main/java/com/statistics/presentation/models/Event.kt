package com.statistics.presentation.models

internal sealed interface Event {
    data object Refresh : Event
    data class FilterVisitorsByTime(val filterTag: String) : Event
    data class FilterVisitorsByPeriod(val filterTag: String) : Event
}