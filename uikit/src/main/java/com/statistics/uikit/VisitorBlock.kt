@file:Suppress("MagicNumber")

package com.statistics.uikit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.statistics.domain.models.EventCounter

@Composable
fun VisitorBlock(
    event: EventCounter,
    modifier: Modifier = Modifier
) {
    val isVisitorNumberIncreased =
        event.visitorsCounterCurrentMonth > event.visitorsCounterPrevMonth
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProgressIcon(isVisitorNumberIncreased)
            VisitorsNumber(event.visitorsCounterCurrentMonth, isVisitorNumberIncreased)
        }
    }
}

@Composable
private fun VisitorsNumber(
    visitorsCounterCurrentMonth: Int,
    isVisitorNumberIncreased: Boolean
) {
    val isIncreasedString = stringResource(
        if (isVisitorNumberIncreased) {
            com.statistics.core.resources.R.string.increased
        } else {
            com.statistics.core.resources.R.string.decreased
        }
    )

    val progressDescription = stringResource(
        com.statistics.core.resources.R.string.visitor_number_is,
        isIncreasedString
    )
    ProgressItemBlock(isVisitorNumberIncreased, visitorsCounterCurrentMonth, progressDescription)

}

@Composable
internal fun ProgressItemBlock(isIncreased: Boolean, value: Int, description: String) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(4.dp))
            ArrowIcon(isIncreased)
        }

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ArrowIcon(isIncreased: Boolean) {
    val arrowIcon = painterResource(
        if (isIncreased) {
            com.statistics.core.resources.R.drawable.arrow_up_ic_17
        } else {
            com.statistics.core.resources.R.drawable.arrow_down_ic_17
        }
    )

    val iconDescription = stringResource(
        if (isIncreased) {
            com.statistics.core.resources.R.string.arrow_up
        } else {
            com.statistics.core.resources.R.string.arrow_down
        }
    )

    Icon(
        painter = arrowIcon,
        contentDescription = iconDescription,
        tint = Color.Unspecified
    )

}

@Composable
internal fun ProgressIcon(isVisitorNumberIncreased: Boolean) {
    val progressIcon = painterResource(
        if (isVisitorNumberIncreased) {
            com.statistics.core.resources.R.drawable.positive_progress_ic_99
        } else {
            com.statistics.core.resources.R.drawable.negative_progress_ic_95
        }
    )
    Icon(
        modifier = Modifier.padding(end = 20.dp),
        painter = progressIcon,
        contentDescription = stringResource(com.statistics.core.resources.R.string.progress_icon),
        tint = Color.Unspecified
    )
}


@Preview
@Composable
private fun VisitorBlockPreview() {
    Column {
        VisitorBlock(EventCounter(243, 160, 12, 43))
        Spacer(modifier = Modifier.height(16.dp))
        VisitorBlock(EventCounter(411, 423, 24, 11))
    }
}
