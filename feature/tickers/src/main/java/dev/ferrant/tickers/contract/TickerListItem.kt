package dev.ferrant.tickers.contract

import dev.ferrant.model.Ticker

sealed class TickerListItem {
    object Skeleton: TickerListItem()

    data class TickerItem(val ticker: Ticker): TickerListItem()
}
