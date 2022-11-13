package dev.ferrant.tickers.contract

import dev.ferrant.core.arch.ViewState

data class TickersViewState(
    val isLoading: Boolean = false,
    val warningMessage: String? = null,
    val errorMessage: String? = null,
    val tickers: List<TickerListItem> = listOf(),
): ViewState
