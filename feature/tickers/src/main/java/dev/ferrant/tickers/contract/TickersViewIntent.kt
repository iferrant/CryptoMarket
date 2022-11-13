package dev.ferrant.tickers.contract

import dev.ferrant.core.arch.ViewIntent

sealed class TickersViewIntent: ViewIntent {
    object OnInit: TickersViewIntent()
}
