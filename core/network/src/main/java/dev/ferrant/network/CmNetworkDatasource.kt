package dev.ferrant.network

import dev.ferrant.network.model.NetworkTicker


interface CmNetworkDatasource {
    suspend fun tickers(symbols: String): List<NetworkTicker>
}

