package dev.ferrant.data.repository

import dev.ferrant.data.model.asEntity
import dev.ferrant.database.dao.TickerDao
import dev.ferrant.database.model.TickerEntity
import dev.ferrant.database.model.asExternalModel
import dev.ferrant.model.Ticker
import dev.ferrant.network.CmNetworkDatasource
import dev.ferrant.network.model.NetworkTicker
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val network: CmNetworkDatasource,
    private val tickerDao: TickerDao,
) : TickerRepository {


    override suspend fun refreshTickers(symbols: String) {
        withContext(ioDispatcher) {
            val tickersEntities = network.tickers(symbols).map(NetworkTicker::asEntity)
            tickerDao.insertTickers(tickersEntities)
        }
    }

    override fun getTickers(query: String): Flow<List<Ticker>> =
        tickerDao.getTickers("%${query}%").map { it.map(TickerEntity::asExternalModel) }

}