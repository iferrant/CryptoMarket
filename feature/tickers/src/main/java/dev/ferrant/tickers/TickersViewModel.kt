package dev.ferrant.tickers

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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class TickersViewModel @Inject constructor(
    private val repository: TickerRepository,
) : BaseViewModel<TickersViewIntent, TickersViewState>() {

    init {
        search()
    }

    override fun createInitialState(): TickersViewState = TickersViewState(isLoading = true)

    override fun Flow<TickersViewIntent>.handleIntent(): Flow<StateReducer<TickersViewState>> {
        val searchFlow = filterIsInstance<TickersViewIntent.OnSearch>()
            .flatMapLatest {
                repository
                    .getTickers(query = it.query)
                    .map<List<Ticker>, TickersStateReducer> { tickers ->
                        TickersStateReducer.TickersList(tickers.map { TickerListItem.TickerItem(it) }
                    )}
            }
            .catch { emit(TickersStateReducer.Error(it.message)) }
            .onStart { emit(TickersStateReducer.Skeletons) }

        val errorFlow = filterIsInstance<TickersViewIntent.OnError>()
            .map { TickersStateReducer.Error(it.message) }

        return merge(
            searchFlow,
            errorFlow,
        )
    }

    fun search(query: String = "") {
        produceIntent(TickersViewIntent.OnSearch(query))
    }

    fun error(message: String?) {
        produceIntent(TickersViewIntent.OnError(message))
    }
}
