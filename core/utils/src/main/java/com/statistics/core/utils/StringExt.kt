package com.statistics.core.utils

import android.util.Log
import java.time.LocalDate

@Suppress("TooGenericExceptionCaught", "MagicNumber")
fun String.toDate(): LocalDate? {
    var result: LocalDate? = null

    if (this.length in 6..8) {
        try {
            val year = this.takeLast(4).toInt()
            val rest = this.dropLast(4)

            val (day, month) = when (rest.length) {
                2 -> Pair(rest[0].digitToInt(), rest[1].digitToInt())
                3 -> Pair(rest.substring(0, 1).toInt(), rest.substring(1).toInt())
                4 -> Pair(rest.substring(0, 2).toInt(), rest.substring(2).toInt())
                else -> null
            } ?: return null

            result = LocalDate.of(year, month, day)
        } catch (e: Exception) {
            Log.e("toDateFormatter", e.message.toString())
        }
    }

    return result
}