package dev.ferrant.tickers.contract

import dev.ferrant.core.arch.ViewIntent

sealed class TickersViewIntent: ViewIntent {
    data class OnRefresh(val symbols: String): TickersViewIntent()
    data class OnSearch(val query: String): TickersViewIntent()
    data class OnError(val message: String?): TickersViewIntent()
}
