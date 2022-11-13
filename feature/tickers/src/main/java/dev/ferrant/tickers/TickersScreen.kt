package dev.ferrant.tickers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.ferrant.model.previewTicker
import dev.ferrant.tickers.contract.TickerListItem
import dev.ferrant.tickers.contract.TickersViewState

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
    )
}

@Composable
private fun TickersScreen(
    modifier: Modifier = Modifier,
    state: TickersViewState,
) {
    if (state.tickers.isNotEmpty()) {
        LazyColumn() {
            items(state.tickers) {
                when(it) {
                    is TickerListItem.TickerItem -> TickerCard(item = it)
                    is TickerListItem.Skeleton -> { }
                }
            }
        }
    }
}

@Composable
private fun TickerCard(
    modifier: Modifier = Modifier,
    item: TickerListItem.TickerItem,
) {
    val dailyChangeRelative = item.ticker.dailyChangeRelative*100
    val isChangePositive = dailyChangeRelative >= 0

    ElevatedCard(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = item.ticker.symbol,
                style = MaterialTheme.typography.titleMedium,
            )

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = item.ticker.lastPrice.toString(),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "$dailyChangeRelative%",
                    style = MaterialTheme.typography.titleSmall,
                    color = if(isChangePositive) Color.Green else Color.Red
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewTickerCard() {
    TickerCard(
        item = TickerListItem.TickerItem(ticker = previewTicker)
    )
}
