package com.statistics.core.resources


import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val PersonFemaleImg: ImageVector
    @Composable
    get() {
        val onBackground = MaterialTheme.colorScheme.onBackground

        _PersonFemalemg = remember(onBackground) {
            ImageVector.Builder(
                name = "PersonFemale",
                defaultWidth = 48.dp,
                defaultHeight = 48.dp,
                viewportWidth = 64f,
                viewportHeight = 64f
            ).apply {
                path(
                    stroke = SolidColor(onBackground),
                    strokeLineWidth = 3f,
                    strokeLineCap = StrokeCap.Round
                ) {
                    moveTo(37.09f, 36.41f)
                    reflectiveCurveToRelative(8.36f, -0.6f, 8.87f, -3.12f)
                    curveToRelative(0f, 0f, -4.13f, -2.22f, -3.33f, -10f)
                    reflectiveCurveToRelative(-1.74f, -11.86f, -6.17f, -10.65f)
                    curveToRelative(0f, 0f, -6.68f, -8f, -12.73f, -0.18f)
                    curveToRelative(0f, 0f, -2.76f, 2.77f, -2.56f, 9.12f)
                    reflectiveCurveToRelative(-0.1f, 9.57f, -3.73f, 11.59f)
                    arcToRelative(
                        10.21f,
                        10.21f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = false,
                        8.77f,
                        3.22f
                    )
                    lineToRelative(0.29f, 4.32f)
                    arcToRelative(
                        0.55f,
                        0.55f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = true,
                        -0.43f,
                        0.56f
                    )
                    lineTo(20.4f, 42.58f)
                    curveToRelative(-4.09f, 1.07f, -7.62f, 3.4f, -9.2f, 7.33f)
                    lineTo(9.79f, 53.43f)
                    arcToRelative(
                        1.11f,
                        1.11f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = false,
                        1f,
                        1.53f
                    )
                    horizontalLineTo(53.18f)
                    arcToRelative(
                        1.11f,
                        1.11f,
                        0f,
                        isMoreThanHalf = false,
                        isPositiveArc = false,
                        1f,
                        -1.53f
                    )
                    lineToRelative(-1.3f, -3.23f)
                    curveToRelative(-1.65f, -4.09f, -5f, -6.49f, -9.31f, -7.44f)
                    lineToRelative(-6.18f, -1.14f)
                    arcTo(0.56f, 0.56f, 0f, isMoreThanHalf = false, isPositiveArc = true, 37f, 41f)
                    close()
                }
            }.build()
        }

        return _PersonFemalemg!!
    }

@Suppress("ObjectPropertyName")
private var _PersonFemalemg: ImageVector? = null


@Preview(
    showBackground = true,
    backgroundColor = 0xFFF6F6F6
)
@Composable
private fun ShowImg() {
    MaterialTheme {
        Image(
            imageVector = PersonFemaleImg,
            contentDescription = null,
        )
    }
}