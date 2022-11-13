package dev.ferrant.data.repository

import dev.ferrant.model.Ticker
import kotlinx.coroutines.flow.Flow

interface TickerRepository {
    fun getTickers(symbols: String): Flow<List<Ticker>>
}
