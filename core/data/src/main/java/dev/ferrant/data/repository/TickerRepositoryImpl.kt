package dev.ferrant.data.repository

import dev.ferrant.data.DataResult
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
import java.net.UnknownHostException
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val network: CmNetworkDatasource,
    private val tickerDao: TickerDao,
) : TickerRepository {

    override suspend fun refreshTickers(): DataResult<List<Ticker>> = withContext(ioDispatcher) {
        try {
            val tickersEntities = network.tickers(USD_SYMBOLS).map(NetworkTicker::asEntity)
            tickerDao.insertTickers(tickersEntities)
            DataResult.Success(tickersEntities.map { it.asExternalModel() })
        } catch (unknownHostException: UnknownHostException){
            DataResult.Failure(unknownHostException.message, unknownHostException)
        } catch (e: Exception) {
            DataResult.Failure(e.message, e)
        }
    }

    override fun getTickers(query: String): Flow<List<Ticker>> =
        tickerDao.getTickers("%${query}%").map { it.map(TickerEntity::asExternalModel) }

    companion object {
        private const val USD_SYMBOLS = "tBTCUSD,tETHUSD,tCHSB:USD,tLTCUSD,tXRPUSD,tDSHUSD," +
                "tRRTUSD,tEOSUSD,tSANUSD,tDATUSD,tSNTUSD,tDOGE:USD,tLUNA:USD,tMATIC:USD," +
                "tNEXO:USD,tOCEAN:USD,tBEST:USD,tAAVE:USD,tPLUUSD,tFILUSD"
    }
}