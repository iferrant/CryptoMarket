package dev.ferrant.tickers.contract

import dev.ferrant.core.arch.ViewState
import java.util.*

data class TickersViewState(
    val isLoading: Boolean = false,
    val isContentOutdated: Boolean = false,
    val lastUpdate: Date? = null,
    val errorMessage: String? = null,
    val tickers: List<TickerListItem> = listOf(),
): ViewState
