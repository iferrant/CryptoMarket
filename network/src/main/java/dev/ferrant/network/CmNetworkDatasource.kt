package dev.ferrant.network


interface CmNetworkDatasource {
    suspend fun tickers(symbols: String)
}

