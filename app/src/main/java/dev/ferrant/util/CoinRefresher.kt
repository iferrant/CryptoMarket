package dev.ferrant.util

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import dev.ferrant.data.DataResult
import dev.ferrant.data.repository.TickerRepository
import dev.ferrant.model.Ticker
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinRefresher @Inject constructor(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val tickerRepository: TickerRepository,
) : DefaultLifecycleObserver {

    private var refreshJob: Job = Job()
    private val scope = CoroutineScope(ioDispatcher + refreshJob)

    private val _resultStateFlow = MutableSharedFlow<DataResult<List<Ticker>>>()
    val resultStateFlow: SharedFlow<DataResult<List<Ticker>>>
        get() = _resultStateFlow

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

        scope.launch {
            while (isActive) {
                val result = tickerRepository.refreshTickers()
                _resultStateFlow.emit(result)
                delay(5000)
            }
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)

        refreshJob.cancelChildren()
    }

}
