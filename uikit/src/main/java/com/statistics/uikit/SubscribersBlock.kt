@file:Suppress("MagicNumber")

package com.statistics.uikit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.statistics.domain.models.EventCounter

@Composable
fun SubscribersBlock(eventCounter: EventCounter) {
    val subscribersCounterCurrentMonth = eventCounter.subscribersCounterCurrentMonth
    val unsubscribersCounterCurrentMonth = eventCounter.unsubscribersCounterCurrentMonth
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column {
            if (subscribersCounterCurrentMonth > 0) {
                val description =
                    stringResource(com.statistics.core.resources.R.string.new_observers_this_month)

                Row(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProgressIcon(true)
                    ProgressItemBlock(true, subscribersCounterCurrentMonth, description)
                }
            }
            if (unsubscribersCounterCurrentMonth > 0) {
                val users = pluralStringResource(
                    com.statistics.core.resources.R.plurals.users_nums,
                    unsubscribersCounterCurrentMonth
                )
                val description =
                    stringResource(
                        com.statistics.core.resources.R.string.users_have_stopped_subscribe_you,
                        users
                    )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.background
                )
                Row(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProgressIcon(false)
                    ProgressItemBlock(false, unsubscribersCounterCurrentMonth, description)
                }
            }
        }
    }
}


@Preview()
@Composable
private fun SubscribersBlockPreview() {
    Column {
        SubscribersBlock(EventCounter(243, 160, 12, 43))
        Spacer(modifier = Modifier.height(16.dp))
        SubscribersBlock(EventCounter(243, 160, 0, 26))
        Spacer(modifier = Modifier.height(16.dp))
        SubscribersBlock(EventCounter(243, 160, 50, 0))
    }
}