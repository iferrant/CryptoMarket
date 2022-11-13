package dev.ferrant.model

import java.util.*

data class Ticker(
    val symbol: String,
    val bid: Float,
    val bidSize: Float,
    val ask: Float,
    val askSize: Float,
    val dailyChange: Float,
    val dailyChangeRelative: Float,
    val lastPrice: Float,
    val volume: Float,
    val high: Float,
    val low: Float,
    val date: Date,
)

val previewTicker = Ticker(
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
    date = Date()
)

val previewTickers = listOf(
    Ticker(
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
        date = Date(),
    ),
    Ticker(
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
        date = Date(),
    ),
    Ticker(
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
        date = Date(),
    ),
    Ticker(
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
        date = Date(),
    ),
)
