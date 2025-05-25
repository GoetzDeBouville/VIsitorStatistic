package com.statistics.uikit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun EmptyScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(com.statistics.core.resources.R.string.no_data_to_show),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFF6F6F6
)
@Composable
private fun EmptyScreenPreview() {
    MaterialTheme {
        EmptyScreen()
    }
}