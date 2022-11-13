package dev.ferrant.data.repository

import dev.ferrant.model.Ticker
import kotlinx.coroutines.flow.Flow

interface TickerRepository {
    suspend fun refreshTickers(symbols: String)
    fun getTickers(query: String): Flow<List<Ticker>>
}
