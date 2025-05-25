@file:OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("MagicNumber")

package com.statistics.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.statistics.core.resources.R
import com.statistics.core.utils.visitorsProgress
import com.statistics.domain.models.EventCounter
import com.statistics.domain.models.EventStatistic
import com.statistics.domain.models.User
import com.statistics.presentation.models.Event
import com.statistics.presentation.models.ScreenState
import com.statistics.uikit.EmptyScreen
import com.statistics.uikit.ErrorScreen
import com.statistics.uikit.GenderPieChartBlock
import com.statistics.uikit.LoadingIndicator
import com.statistics.uikit.OftenVisitorsBlock
import com.statistics.uikit.SelectableFilterChips
import com.statistics.uikit.SubscribersBlock
import com.statistics.uikit.ViewsChartBlock
import com.statistics.uikit.VisitorBlock
import java.time.LocalDate

@Composable
fun UserStatisticScreen(
    isWideDisplay: Boolean,
    navController: NavController,
    viewModel: UserStatisticVM = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    Content(
        state,
        viewModel::accept
    )
}

@Composable
private fun Content(
    state: ScreenState,
    accept: (Event) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.statistic),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { paddingValues ->
        when {
            state.isLoading -> LoadingIndicator()
            state.errorType != null -> ErrorScreen(state.errorType)
            else -> {
                Body(
                    users = state.users,
                    eventList = state.events,
                    paddingValues = paddingValues,
                    accept = accept
                )
            }
        }
    }
}


@Composable
private fun Body(
    users: List<User>,
    eventList: List<EventStatistic>,
    paddingValues: PaddingValues,
    accept: (Event) -> Unit,
) {
    Log.i("UserStatisticScreen", "eventList = $eventList")

    if (eventList.isEmpty()) {
        EmptyScreen()
    } else {
        BodyContainer(
            eventList = eventList,
            accept = accept,
            paddingValues = paddingValues,
            users = users
        )
    }
}

@Composable
private fun BodyContainer(
    eventList: List<EventStatistic>,
    accept: (Event) -> Unit,
    paddingValues: PaddingValues,
    users: List<User>
) {
    val scrollState = rememberScrollState()
//    val eventCounter = eventList.visitorsProgress(LocalDate.now())
    val eventCounter = eventList.visitorsProgress(LocalDate.of(2024, 9, 30)) // устанавливаем дату для демонстрации

    Log.i(
        "UserStatisticScreen",
        "eventList = $eventList\nusers = $users\neventCounter = $eventCounter"
    )

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(scrollState)
    ) {
        FirstBlock(
            eventStats = eventCounter,
            accept = accept,
            statisticData = eventList,
            users = users
        )
        SecondBlock(
            accept = accept,
            statisticData = eventList,
            users = users,
            eventCounter = eventCounter
        )
    }
}

@Composable
private fun FirstBlock(
    modifier: Modifier = Modifier,
    eventStats: EventCounter,
    accept: (Event) -> Unit,
    statisticData: List<EventStatistic>,
    users: List<User>
) {
    val filterList = listOf<String>(
        stringResource(R.string.by_days),
        stringResource(R.string.by_weeks),
        stringResource(R.string.by_months)
    )
    val oftenVisitors = statisticData.filter {
        it.type == "view"
    }.sortedBy { eventStat ->
        eventStat.dates.size
    }.mapNotNull { event ->
        users.find { user ->
            user.id == event.userId
        }
    }
        .toSet()
        .take(3)

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(
            text = stringResource(R.string.visitors),
            style = MaterialTheme.typography.titleMedium
        )

        VisitorBlock(
            event = eventStats,
            modifier = Modifier.padding(top = 12.dp)
        )

        SelectableFilterChips(
            modifier = Modifier.padding(top = 28.dp),
            options = filterList,
            selectedOption = filterList[0],
            onOptionSelected = { selected ->
                accept(Event.FilterVisitorsByTime(selected))
            }
        )

        ViewsChartBlock(
            modifier = Modifier.padding(top = 12.dp),
            stats = statisticData
        )

        Text(
            modifier = Modifier.padding(top = 28.dp),
            text = stringResource(R.string.most_often_visit_your_profile),
            style = MaterialTheme.typography.titleMedium
        )

        OftenVisitorsBlock(
            modifier = Modifier.padding(top = 12.dp),
            users = oftenVisitors
        )
    }
}

@Composable
private fun SecondBlock(
    modifier: Modifier = Modifier,
    accept: (Event) -> Unit,
    statisticData: List<EventStatistic>,
    users: List<User>,
    eventCounter: EventCounter
) {
    val listOfPeriod = listOf<String>(
        stringResource(R.string.today),
        stringResource(R.string.week),
        stringResource(R.string.month),
        stringResource(R.string.all_the_time)
    )

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(
            modifier = Modifier.padding(top = 28.dp),
            text = stringResource(R.string.sex_and_age),
            style = MaterialTheme.typography.titleMedium
        )

        SelectableFilterChips(
            modifier = Modifier.padding(top = 28.dp),
            options = listOfPeriod,
            selectedOption = listOfPeriod[0],
            onOptionSelected = { selected ->
                accept(Event.FilterVisitorsByPeriod(selected))
            }
        )

        GenderPieChartBlock(
            modifier = Modifier.padding(top = 12.dp),
            stats = statisticData,
            users = users
        )

        Text(
            modifier = Modifier.padding(top = 28.dp),
            text = stringResource(R.string.subscribers),
            style = MaterialTheme.typography.titleMedium
        )

        SubscribersBlock(
            modifier = Modifier.padding(top = 28.dp),
            eventCounter = eventCounter
        )
    }
}