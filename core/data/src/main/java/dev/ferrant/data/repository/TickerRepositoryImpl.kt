package dev.ferrant.data.repository

import dev.ferrant.data.model.asExternalModel
import dev.ferrant.model.Ticker
import dev.ferrant.network.CmNetworkDatasource
import dev.ferrant.network.model.NetworkTicker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor(
    private val network: CmNetworkDatasource
) : TickerRepository {

    override fun getTickers(symbols: String): Flow<List<Ticker>> = flow {
        emit(network.tickers(symbols).map(NetworkTicker::asExternalModel))
    }

}