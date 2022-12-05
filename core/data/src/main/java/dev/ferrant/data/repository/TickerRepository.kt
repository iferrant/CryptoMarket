package dev.ferrant.data.repository

import dev.ferrant.data.DataResult
import dev.ferrant.model.Ticker
import kotlinx.coroutines.flow.Flow

interface TickerRepository {
    suspend fun refreshTickers(): DataResult<List<Ticker>>
    fun getTickers(query: String): Flow<List<Ticker>>
}
