package com.statistics.visitorstatistic.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.statistics.core.presentation.utils.isWideDisplay
import com.statistics.visitorstatistic.presentation.nav.AppNavGraph
import com.statistics.visitorstatistic.ui.theme.VisitorStatisticTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VisitorStatisticTheme {
                val isWideDisplay = isWideDisplay(this)
                AppNavGraph(isWideDisplay)
            }
        }
    }
}
