package dev.ferrant.network.model

import com.google.gson.annotations.SerializedName

data class NetworkTicker(
    val symbol: String,
    val bid: Float,
    @SerializedName("bid_size")
    val bidSize: Int,
    val ask: Float,
    @SerializedName("ask_size")
    val askSize: Float,
    @SerializedName("daily_change")
    val dailyChange: Float,
    @SerializedName("daily_change_relative")
    val dailyChangeRelative: Float,
    @SerializedName("last_price")
    val lastPrice: Float,
    val volume: Float,
    val high: Float,
    val low: Float,
)
