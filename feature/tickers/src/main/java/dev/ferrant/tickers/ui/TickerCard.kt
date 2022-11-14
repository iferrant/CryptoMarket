package dev.ferrant.tickers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ferrant.model.previewTicker
import dev.ferrant.tickers.contract.TickerListItem
import dev.ferrant.ui.loadingShimmerEffect


@Composable
fun TickerCard(
    modifier: Modifier = Modifier,
    item: TickerListItem.TickerItem,
) {
    val dailyChangeRelative = item.ticker.dailyChangeRelative*100
    val isChangePositive = dailyChangeRelative >= 0

    Card(
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
                    text = String.format("%.2f", dailyChangeRelative).plus("%"),
                    style = MaterialTheme.typography.titleSmall,
                    color = if(isChangePositive) Color(0xFF00C900) else Color.Red
                )
            }
        }
    }
}

@Composable
fun TickerSkeleton(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Spacer(
                modifier = Modifier
                    .size(width = 96.dp, height = 24.dp)
                    .background(
                        brush = loadingShimmerEffect(),
                        shape = RoundedCornerShape(4.dp),
                    )
            )

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {

                Spacer(
                    modifier = Modifier
                        .size(width = 96.dp, height = 24.dp)
                        .background(
                            brush = loadingShimmerEffect(),
                            shape = RoundedCornerShape(4.dp),
                        )
                )

                Spacer(
                    modifier = Modifier
                        .size(width = 56.dp, height = 20.dp)
                        .background(
                            brush = loadingShimmerEffect(),
                            shape = RoundedCornerShape(4.dp),
                        )
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

@Preview
@Composable
fun PreviewTickerSkeleton() {
    TickerSkeleton()
}
