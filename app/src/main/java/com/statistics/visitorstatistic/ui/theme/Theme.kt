package com.statistics.visitorstatistic.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Red,
    onPrimary = Color.White,
    secondary = MediumGray,
    onSecondary = Green,
    background = LightGray,
    surface = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    surfaceVariant = Orange,
    error = Red,
    outline = DarkGray,
)


@Composable
fun VisitorStatisticTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}