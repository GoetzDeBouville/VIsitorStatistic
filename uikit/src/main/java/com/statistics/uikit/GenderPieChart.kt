@file:Suppress("UnderscoresInNumericLiterals", "MagicNumber")

package com.statistics.uikit

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.statistics.domain.models.EventStatistic
import com.statistics.domain.models.User
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.Pie

@Composable
fun GenderPieChartBlock(
    modifier: Modifier = Modifier,
    stats: List<EventStatistic>,
    users: List<User>,
) {
    val (maleCount, femaleCount) = remember(stats, users) {
        stats
            .filter { it.type == "view" }
            .flatMap { it.dates.map { ev -> it.userId } }
            .mapNotNull { id -> users.find { it.id == id } }
            .groupingBy { it.sex }
            .eachCount()
            .let { counts ->
                val m = counts["M"] ?: 0
                val f = counts["W"] ?: 0
                m to f
            }
    }

    val total = (maleCount + femaleCount).takeIf { it > 0 } ?: 1
    val malePercent = maleCount * 100.0 / total
    val femalePercent = femaleCount * 100.0 / total

    var selectedIndex by remember { mutableIntStateOf(-1) }
    val pieData = listOf(
        Pie(data = malePercent, color = MaterialTheme.colorScheme.primary, selected = selectedIndex == 0),
        Pie(data = femalePercent, color = MaterialTheme.colorScheme.surfaceVariant, selected = selectedIndex == 1)
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PieChart(
                modifier = Modifier
                    .size(180.dp),
                data = pieData,
                selectedScale = 1.1f,
                onPieClick = { pie ->
                    val idx = pieData.indexOf(pie)
                    selectedIndex = if (selectedIndex == idx) -1 else idx
                },
                scaleAnimEnterSpec = spring(stiffness = Spring.StiffnessLow),
                colorAnimEnterSpec = tween(300),
                colorAnimExitSpec = tween(300),
                style = Pie.Style.Stroke(width = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LegendItem(
                    color = MaterialTheme.colorScheme.primary,
                    text = "Мужчины ${"%.0f".format(malePercent)}%"
                )
                LegendItem(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    text = "Женщины ${"%.0f".format(femalePercent)}%"
                )
            }
        }
    }
}

@Composable
private fun LegendItem(
    color: Color,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(Modifier.width(8.dp))
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun GenderPieChartBlockPreview() {
    MaterialTheme {
        val testUsers = listOf(
            User(
                id = 1,
                age = 25,
                files = emptyList(),
                isOnline = true,
                sex = "male",
                username = "user1"
            ),
            User(
                id = 2,
                age = 30,
                files = emptyList(),
                isOnline = false,
                sex = "female",
                username = "user2"
            ),
            User(
                id = 3,
                age = 22,
                files = emptyList(),
                isOnline = true,
                sex = "male",
                username = "user3"
            ),
            User(
                id = 4,
                age = 28,
                files = emptyList(),
                isOnline = false,
                sex = "female",
                username = "user4"
            ),
            User(
                id = 5,
                age = 35,
                files = emptyList(),
                isOnline = true,
                sex = "male",
                username = "user5"
            )
        )

        val testStats = listOf(
            EventStatistic(
                dates = listOf(1012023, 2012023),
                type = "view",
                userId = 1
            ),
            EventStatistic(
                dates = listOf(1012023),
                type = "view",
                userId = 2
            ),
            EventStatistic(
                dates = listOf(1012023, 2012023, 3012023),
                type = "view",
                userId = 3
            ),
            EventStatistic(
                dates = listOf(1012023, 2012023),
                type = "view",
                userId = 4
            ),
            EventStatistic(
                dates = listOf(1012023),
                type = "click",
                userId = 5
            )
        )

        GenderPieChartBlock(
            modifier = Modifier.padding(16.dp),
            stats = testStats,
            users = testUsers
        )
    }
}
