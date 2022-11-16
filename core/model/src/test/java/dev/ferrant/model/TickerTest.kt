package dev.ferrant.model

import org.junit.Assert.assertEquals
import org.junit.Test

class TickerTest {

    @Test
    fun formatsShortSymbol() {
        assertEquals(testTickerWithShortSymbol.formatSymbol(), shortSymbolFormatted)
    }

    @Test
    fun formatsLongSymbol() {
        assertEquals(testTickerWithLongSymbol.formatSymbol(), longSymbolFormatted)
    }
}

private val shortSymbolFormatted = Pair("BTC", "USD")
private val longSymbolFormatted = Pair("DOGE", "USD")

private val testTickerWithShortSymbol = Ticker(
    symbol = "tBTCUSD",
    bid = 17783f,
    bidSize = 49.24085298f,
    ask = 17784f,
    askSize = 179.50908914f,
    dailyChange = 474f,
    dailyChangeRelative = 0.0274f,
    lastPrice = 17784f,
    volume = 25373.17831895f,
    high = 17956f,
    low = 15588f,
    date = 15588,
)

private val testTickerWithLongSymbol = Ticker(
    symbol = "tDOGE:USD",
    bid = 17783f,
    bidSize = 49.24085298f,
    ask = 17784f,
    askSize = 179.50908914f,
    dailyChange = 474f,
    dailyChangeRelative = 0.0274f,
    lastPrice = 17784f,
    volume = 25373.17831895f,
    high = 17956f,
    low = 15588f,
    date = 15588,
)
