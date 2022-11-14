package dev.ferrant.tickers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.ferrant.model.previewTickers
import dev.ferrant.tickers.R
import dev.ferrant.tickers.TickersViewModel
import dev.ferrant.tickers.contract.TickerListItem
import dev.ferrant.tickers.contract.TickersViewState
import dev.ferrant.tickers.extensions.toDateFormat
import dev.ferrant.tickers.extensions.toTimeFormat
import java.util.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun TickersRoute(
    modifier: Modifier = Modifier,
    viewModel: TickersViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    TickersScreen(
        modifier = modifier,
        state = state,
        onSearch = viewModel::search
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TickersScreen(
    modifier: Modifier = Modifier,
    state: TickersViewState,
    onSearch: (String) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {

            var text by remember { mutableStateOf(TextFieldValue("")) }
            TickersHeader(value = text) {
                text = it
                onSearch(it.text)
            }

            TickersContent(state = state)
        }
    }
}

@Composable
fun TickersHeader(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.primary)
    ) {
        Divider(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onPrimary.copy(0.3f)
        )

        SearchBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = value,
            onValueChange = onValueChange,
        )
    }
}

@Composable
fun TickersContent(
    modifier: Modifier = Modifier,
    state: TickersViewState,
) {

    if (state.isContentOutdated && state.tickers.isNotEmpty()) {
        TickersWarningMessage(items = state.tickers)
    }

    if (state.tickers.isNotEmpty()) {
        LazyColumn(
            modifier = modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            items(state.tickers) {
                when (it) {
                    is TickerListItem.TickerItem -> TickerCard(item = it)
                    is TickerListItem.Skeleton -> TickerSkeleton()
                }
            }
        }
    } else if(state.isLoading.not()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.tickers_list_empty_state),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                style = MaterialTheme.typography.displaySmall,
            )
        }
    }
}

@Composable
fun TickersWarningMessage(items: List<TickerListItem>) {
    val item = items[0]
    if (item is TickerListItem.TickerItem) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Last update: ${item.ticker.date.toDateFormat()} at ${item.ticker.date.toTimeFormat()}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
            )
        }
    }
}

@Preview
@Composable
fun PreviewTickersHeader() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TickersHeader(value = text) { text = it }
}

@Preview
@Composable
fun PreviewTickersContent() {
    val items = previewTickers.map { TickerListItem.TickerItem(it) }
    TickersContent(state = TickersViewState(tickers = items))
}

@Preview
@Composable
fun PreviewTickersWarningMessage() {
    val items = previewTickers.map { TickerListItem.TickerItem(it) }
    TickersWarningMessage(items = items)
}
