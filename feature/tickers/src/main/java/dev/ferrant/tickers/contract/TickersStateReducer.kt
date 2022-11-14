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
        private val errorMessage: String? = null,
    ): TickersStateReducer() {
        override fun reduce(initialState: TickersViewState): TickersViewState =
            initialState.copy(
                isLoading = false,
                tickers = tickers,
                errorMessage = errorMessage,
            )
    }

    object Refresh: TickersStateReducer() {
        override fun reduce(initialState: TickersViewState): TickersViewState =
            initialState.copy(
                isContentOutdated = isContentOutdated(initialState.tickers),
            )
    }

    data class Error(
        private val errorMessage: String? = null,
    ): TickersStateReducer() {
        override fun reduce(initialState: TickersViewState): TickersViewState =
            initialState.copy(
                isLoading = false,
                errorMessage = errorMessage,
                isContentOutdated = true,
            )
    }

}

private fun isContentOutdated(tickers: List<TickerListItem>): Boolean {
    if (tickers.isNotEmpty()) {
        val item = tickers[0]
        if (item is TickerListItem.TickerItem) {
            val duration = System.currentTimeMillis() - item.ticker.date
            return duration > 6000
        }
    }
    return false
}
