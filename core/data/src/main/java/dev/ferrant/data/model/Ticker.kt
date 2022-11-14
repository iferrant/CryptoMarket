package dev.ferrant.data.model

import dev.ferrant.database.model.TickerEntity
import dev.ferrant.network.model.NetworkTicker
import java.util.*

fun NetworkTicker.asEntity() = TickerEntity(
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
    date = Calendar.getInstance().timeInMillis,
)
