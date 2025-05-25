@file:Suppress("TooGenericExceptionCaught", "MagicNumber")

package com.statistics.core.utils

import java.time.LocalDate

@Suppress("TooGenericExceptionCaught", "MagicNumber")
fun Int.toDate(): LocalDate? {
    var result: LocalDate? = null
    val stringDate = this.toString()

    if (stringDate.length in 6..8) {
        try {
            val year = stringDate.takeLast(4).toInt()
            val rest = stringDate.dropLast(4)

            val (day, month) = when (rest.length) {
                2 -> Pair(rest[0].digitToInt(), rest[1].digitToInt())
                3 -> Pair(rest.substring(0, 1).toInt(), rest.substring(1).toInt())
                4 -> Pair(rest.substring(0, 2).toInt(), rest.substring(2).toInt())
                else -> null
            } ?: return null

            result = LocalDate.of(year, month, day)
        } catch (e: Exception) {
            println("toDateFormatter ${e.message}")
        }
    }

    return result
}

fun Int.declensionUsers(): String {
    val lastTwoDigits = this % 100
    val lastDigit = this % 10

    return when {
        lastTwoDigits in 11..19 -> "$this пользователей"
        lastDigit == 1 -> "$this пользователь"
        lastDigit in 2..4 -> "$this пользователя"
        else -> "$this пользователей"
    }
}