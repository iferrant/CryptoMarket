package dev.ferrant.tickers

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ferrant.core.arch.BaseViewModel
import dev.ferrant.core.arch.StateReducer
import dev.ferrant.data.repository.TickerRepository
import dev.ferrant.tickers.contract.TickerListItem
import dev.ferrant.tickers.contract.TickersStateReducer
import dev.ferrant.tickers.contract.TickersViewIntent
import dev.ferrant.tickers.contract.TickersViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@OptIn(
    ExperimentalCoroutinesApi::class,
    FlowPreview::class,
)
@HiltViewModel
class TickersViewModel @Inject constructor(
    private val repository: TickerRepository,
) : BaseViewModel<TickersViewIntent, TickersViewState>() {

    init {
        produceIntent(TickersViewIntent.OnInit)
    }

    override fun createInitialState(): TickersViewState = TickersViewState(isLoading = true)

    override fun Flow<TickersViewIntent>.handleIntent(): Flow<StateReducer<TickersViewState>> {
        val initialFlow = filterIsInstance<TickersViewIntent>()
            .flatMapConcat {
                repository
                    .getTickers(symbols = DEFAULT_SYMBOLS)
                    .map { tickers -> TickersStateReducer.TickersList(
                        tickers.map { TickerListItem.TickerItem(it) }
                    )}
            }
            .onStart { TickersStateReducer.Skeletons }

        return merge(
            initialFlow,
        )
    }

    companion object {
        private const val DEFAULT_SYMBOLS = "tBTCUSD,tETHUSD,tCHSB:USD,tLTCUSD,tXRPUSD,tDSHUSD," +
                "tRRTUSD,tEOSUSD,tSANUSD,tDATUSD,tSNTUSD,tDOGE:USD,tLUNA:USD,tMATIC:USD," +
                "tNEXO:USD,tOCEAN:USD,tBEST:USD,tAAVE:USD,tPLUUSD,tFILUSD"
    }
}
