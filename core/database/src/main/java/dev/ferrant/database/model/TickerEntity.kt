package dev.ferrant.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.ferrant.model.Ticker
import java.util.*

@Entity(
    tableName = "tickers"
)
data class TickerEntity(
    @PrimaryKey
    val symbol: String,
    val bid: Float,
    @ColumnInfo(name = "bid_size")
    val bidSize: Float,
    val ask: Float,
    @ColumnInfo(name = "ask_size")
    val askSize: Float,
    @ColumnInfo(name = "daily_change")
    val dailyChange: Float,
    @ColumnInfo(name = "daily_change_relative")
    val dailyChangeRelative: Float,
    @ColumnInfo(name = "last_price")
    val lastPrice: Float,
    val volume: Float,
    val high: Float,
    val low: Float,
    val date: Date,
)

fun TickerEntity.asExternalModel() = Ticker(
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
    date = date,
)
