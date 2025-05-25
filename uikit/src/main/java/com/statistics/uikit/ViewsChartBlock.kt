package com.statistics.uikit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.statistics.core.utils.filterVewEventList
import com.statistics.core.utils.toDate
import com.statistics.domain.models.EventStatistic
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun ViewsChartBlock(
    modifier: Modifier = Modifier,
    stats: List<EventStatistic>,
) {
    val viewsStats = stats.filterVewEventList()
    Card(modifier = modifier) {
        ViewsChart(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
            stats = viewsStats
        )
    }
}

@Composable
private fun ViewsChart(
    modifier: Modifier = Modifier,
    stats: List<EventStatistic>
) {
    // 1. Группируем и считаем «view» по дате
    val countsByDate: List<Pair<LocalDate, Int>> = remember(stats) {
        stats
            .flatMap { ev -> ev.dates.mapNotNull { it.toDate() } }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedBy { it.first }
    }

    if (countsByDate.isEmpty()) return

    val dateLabels = countsByDate.map { it.first }
    val values = countsByDate.map { it.second.toFloat() }

    val modelProducer = remember { CartesianChartModelProducer() }
    LaunchedEffect(values) {
        modelProducer.runTransaction {
            lineSeries { series(values) }
        }
    }

    val dateFormatter = remember {
        CartesianValueFormatter { _, value, _ ->
            val idx = value.roundToInt().coerceIn(dateLabels.indices)
            dateLabels[idx].format(DateTimeFormatter.ofPattern("d.MM"))
        }
    }

    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberLineCartesianLayer(),
            startAxis = VerticalAxis.rememberStart(),
            bottomAxis = HorizontalAxis.rememberBottom(
                valueFormatter = dateFormatter
            )
        ),
        modelProducer = modelProducer,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}
