package dev.ferrant.tickers

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ferrant.core.arch.BaseViewModel
import dev.ferrant.core.arch.StateReducer
import dev.ferrant.data.repository.TickerRepository
import dev.ferrant.model.Ticker
import dev.ferrant.tickers.contract.TickerListItem
import dev.ferrant.tickers.contract.TickersStateReducer
import dev.ferrant.tickers.contract.TickersViewIntent
import dev.ferrant.tickers.contract.TickersViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class TickersViewModel @Inject constructor(
    private val repository: TickerRepository,
) : BaseViewModel<TickersViewIntent, TickersViewState>() {

    init {
        viewModelScope.launch {
            while (isActive) {
                produceIntent(TickersViewIntent.OnRefresh(DEFAULT_SYMBOLS))
                delay(5000)
            }
        }
        search()
    }

    override fun createInitialState(): TickersViewState = TickersViewState(isLoading = true)

    override fun Flow<TickersViewIntent>.handleIntent(): Flow<StateReducer<TickersViewState>> {
        val refreshFlow = filterIsInstance<TickersViewIntent.OnRefresh>()
            .onEach { repository.refreshTickers(it.symbols) }
            .map<TickersViewIntent, TickersStateReducer> { TickersStateReducer.Refresh }
            .catch { emit(TickersStateReducer.Error(it.message)) }
            .onStart { emit(TickersStateReducer.Skeletons) }

        val searchFlow = filterIsInstance<TickersViewIntent.OnSearch>()
            .flatMapLatest {
                repository
                    .getTickers(query = it.query)
                    .map<List<Ticker>, TickersStateReducer> { tickers ->
                        TickersStateReducer.TickersList(tickers.map { TickerListItem.TickerItem(it) }
                    )}
            }

        return merge(
            refreshFlow,
            searchFlow,
        )
    }

    fun search(query: String = "") {
        produceIntent(TickersViewIntent.OnSearch(query))
    }

    companion object {
        private const val DEFAULT_SYMBOLS = "tBTCUSD,tETHUSD,tCHSB:USD,tLTCUSD,tXRPUSD,tDSHUSD," +
                "tRRTUSD,tEOSUSD,tSANUSD,tDATUSD,tSNTUSD,tDOGE:USD,tLUNA:USD,tMATIC:USD," +
                "tNEXO:USD,tOCEAN:USD,tBEST:USD,tAAVE:USD,tPLUUSD,tFILUSD"
    }
}
