package dev.ferrant.tickers.contract

import dev.ferrant.core.arch.StateReducer

sealed class TickersStateReducer: StateReducer<TickersViewState> {

    object Skeletons: TickersStateReducer() {
        override fun reduce(initialState: TickersViewState): TickersViewState =
            initialState.copy(
                tickers = arrayListOf<TickerListItem.Skeleton>().apply {
                    for (i in 1..20) add(TickerListItem.Skeleton)
                }
            )
    }

    class TickersList(
        private val tickers: List<TickerListItem.TickerItem>,
        private val warningMessage: String? = null,
        private val errorMessage: String? = null,
    ): TickersStateReducer() {
        override fun reduce(initialState: TickersViewState): TickersViewState =
            initialState.copy(
                isLoading = false,
                tickers = tickers,
                warningMessage = warningMessage,
                errorMessage = errorMessage,
            )
    }

}
