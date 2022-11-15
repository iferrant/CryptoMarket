package dev.ferrant.tickers

import android.accounts.NetworkErrorException
import dev.ferrant.data.repository.TickerRepository
import dev.ferrant.model.Ticker
import dev.ferrant.test.BaseViewModelTest
import dev.ferrant.test.TestException
import dev.ferrant.tickers.contract.TickerListItem
import dev.ferrant.tickers.contract.TickersViewIntent
import dev.ferrant.tickers.contract.TickersViewState
import dev.ferrant.tickers.ui.TickerSkeleton
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import java.time.Instant

class TickersViewModelTest : BaseViewModelTest<TickersViewIntent, TickersViewState>() {

    private val tickerRepository = mockk<TickerRepository>(relaxed = true)

    override val tested: TickersViewModel by lazy {
        TickersViewModel(tickerRepository)
    }

    private val tickersInitialState = TickersViewState(isLoading = true)

    @Test
    fun `WHEN intent OnRefresh is received THEN state contains flag of content outdated and list of tickers`() {

        val statesExpected = listOf(
            tickersInitialState,
            TickersViewState(
                isLoading = false,
                isContentOutdated = true,
                tickers = listOf(testTickerListItem()),
                errorMessage = null,
            ),
        )
        val intentsToSend = listOf(TickersViewIntent.OnRefresh(""))

        every { tickerRepository.getTickers("") }.answers { flowOf(listOf(testTicker())) }

        assertIntentsOutput(intentsToSend, statesExpected)
    }

    @Test
    fun `WHEN intent OnSearch is received THEN state contains list of tickers`() {

        val statesExpected = listOf(
            tickersInitialState,
            TickersViewState(tickers = listOf()),
        )
        val intentsToSend = listOf(TickersViewIntent.OnSearch(""))

        every { tickerRepository.getTickers("") }.answers { flowOf(listOf()) }

        assertIntentsOutput(intentsToSend, statesExpected)
    }

    @Test
    fun `WHEN intent OnRefresh is received THEN state contains an error and is outdated`() {

        val exceptionMessage = "Connection error"

        val statesExpected = listOf(
            tickersInitialState,
            TickersViewState(
                isContentOutdated = true,
                errorMessage = exceptionMessage,
                tickers = testTickerListItemSkeletons(),
            ),
        )
        val intentsToSend = listOf(TickersViewIntent.OnSearch(""))

        coEvery { tickerRepository.refreshTickers(TickersViewModel.DEFAULT_SYMBOLS) }.throws(
            TestException(message = exceptionMessage)
        )

        assertIntentsOutput(intentsToSend, statesExpected)
    }

}

private fun testTicker() = Ticker(
    symbol = "",
    bid = 0f,
    bidSize = 0f,
    ask = 0f,
    askSize = 0f,
    dailyChange = 0f,
    dailyChangeRelative = 0f,
    lastPrice = 0f,
    volume = 0f,
    high = 0f,
    low = 0f,
    date = 1668525601152
)

private fun testTickerListItemSkeletons() = arrayListOf<TickerListItem.Skeleton>().apply {
    for (i in 1..20) add(TickerListItem.Skeleton)
}

private fun testTickerListItem() = TickerListItem.TickerItem(
    ticker = testTicker()
)
