package com.statistics.uikit

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.statistics.core.resources.NothingFoundImg
import com.statistics.core.resources.R
import com.statistics.core.resources.SomethingWentWrongImg

enum class ErrorScreenState(
    val errorImg: @Composable () -> ImageVector,
    val errorDescriptionResId: Int
) {
    NO_INTERNET(
        { SomethingWentWrongImg },
        R.string.error_something_went_wrong
    ),
    SERVER_ERROR(
        { SomethingWentWrongImg },
        R.string.error_something_went_wrong
    ),
    NOTHING_FOUND(
        { NothingFoundImg },
        R.string.error_nothing_found
    )
}