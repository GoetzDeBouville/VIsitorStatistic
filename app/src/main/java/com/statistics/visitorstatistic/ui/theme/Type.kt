package com.statistics.visitorstatistic.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.statistics.core.resources.R

val Gilroy = FontFamily(
    Font(R.font.gilroy_bold, FontWeight.Bold),
    Font(R.font.gilroy_semibold, FontWeight.SemiBold),
    Font(R.font.gilroy_medium, FontWeight.Medium),
    Font(R.font.gilroy_regular, FontWeight.Normal)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = Color.Black
    ),
    titleMedium = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color.Black
    ),
    titleSmall = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        color = Color.Black
    ),
    bodyMedium = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        color = Color.Black,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        color = Color.Black
    ),
    labelSmall = TextStyle(
        fontFamily = Gilroy,
        fontWeight = FontWeight.Light,
        fontSize = 13.sp,
        color = Color.Black
    )
)