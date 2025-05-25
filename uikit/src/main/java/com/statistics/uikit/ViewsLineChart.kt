package com.statistics.uikit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.statistics.core.utils.declensionUsers
import com.statistics.core.utils.filterVewEventList
import com.statistics.core.utils.toDate
import com.statistics.domain.models.EventStatistic
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DotProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.GridProperties.AxisProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.PopupProperties
import java.time.LocalDate

@Composable
fun ViewsChartBlock(
    modifier: Modifier = Modifier,
    stats: List<EventStatistic>,
) {
    val viewsStats = stats.filterVewEventList()
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        VisitorsLineChart(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
            stats = viewsStats
        )
    }
}

@Composable
private fun VisitorsLineChart(
    stats: List<EventStatistic>,
    modifier: Modifier = Modifier
) {
    val countsByDate: List<Pair<LocalDate, Int>> = remember(stats) {
        stats
            .filter { it.type == "view" }
            .flatMap { ev -> ev.dates.mapNotNull { it.toDate() } }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedBy { it.first }
    }

    if (countsByDate.isEmpty()) return

    val dateLabels = countsByDate.map { date ->
        "%02d.%02d".format(date.first.dayOfMonth, date.first.monthValue)
    }
    val values = countsByDate.map { it.second.toDouble() }

    val line = Line(
        label = "",
        values = values,
        color = SolidColor(Color.Red),
        curvedEdges = false,
        drawStyle = DrawStyle.Stroke(3.dp),
        dotProperties = DotProperties(
            enabled = true,
            radius = 5.dp,
            color = SolidColor(Color.Red),
            strokeWidth = 3.dp,
            strokeColor = SolidColor(Color.White)
        )
    )

    LineChart(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth()
            .height(208.dp),
        data = listOf(line),
        animationMode = AnimationMode.Together(delayBuilder = {
            it * 500L
        }),
        gridProperties = GridProperties(
            xAxisProperties = AxisProperties(
                enabled = true,
            ),
            yAxisProperties = AxisProperties(enabled = true)
        ),
        labelProperties = LabelProperties(
            enabled = true,
            labels = dateLabels
        ),
        popupProperties = PopupProperties(
            enabled = true,
            containerColor = Color.White,
            textStyle = MaterialTheme.typography.labelMedium
                .copy(color = Color.Black),
            cornerRadius = 12.dp,
            contentHorizontalPadding = 8.dp,
            contentVerticalPadding = 12.dp,
            mode = PopupProperties.Mode.PointMode(16.dp),
            contentBuilder = { _, valueIndex, _ ->
                val count = values.getOrNull(valueIndex)?.toInt() ?: 0
                val date = dateLabels.getOrNull(valueIndex) ?: ""
                count.declensionUsers() + "\n$date"
            }
        )
    )
}