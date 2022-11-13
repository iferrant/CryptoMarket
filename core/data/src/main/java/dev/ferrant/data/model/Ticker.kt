package dev.ferrant.data.model

import dev.ferrant.model.Ticker
import dev.ferrant.network.model.NetworkTicker
import java.util.*

fun NetworkTicker.asExternalModel() = Ticker(
    symbol = symbol,
    bid = bid,
    bidSize = bidSize,
    ask = ask,
    askSize = askSize,
    dailyChange = dailyChange,
    dailyChangeRelative = dailyChangeRelative,
    lastPrice = lastPrice,
    volume = volume,
    high = high,
    low = low,
    date = Date()
)
