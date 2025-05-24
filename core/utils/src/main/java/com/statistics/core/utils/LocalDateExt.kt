package com.statistics.core.utils

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

fun LocalDate.toDayMonth(): String {
    val day = this.dayOfMonth
    val month = this.month.getDisplayName(TextStyle.FULL, Locale("ru")).lowercase()
    return "$day $month"
}