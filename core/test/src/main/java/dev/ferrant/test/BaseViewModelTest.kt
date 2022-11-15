package dev.ferrant.test

import androidx.lifecycle.viewModelScope
import dev.ferrant.core.arch.BaseViewModel
import dev.ferrant.core.arch.ViewIntent
import dev.ferrant.core.arch.ViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseViewModelTest<VI : ViewIntent, VS : ViewState> {

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    abstract val tested: BaseViewModel<VI, VS>

    private fun stateCollectionList() = mutableListOf<VS>()

    protected fun assertIntentsOutput(
        intentsToSend: List<VI>,
        statesExpected: List<VS>,
    ) {
        val statesActual = stateCollectionList()

        intentsToSend.forEach(tested::produceIntent)

        val collectionJob = launchStateCollectionJob(statesActual)

        assert(statesExpected.size == statesActual.size)

        statesExpected.zip(statesActual) { expected, actual ->
            assert(expected == actual)
        }

        collectionJob.cancel()
    }

    private fun launchStateCollectionJob(stateCollectionList: MutableList<VS>) =
        tested.viewModelScope.launch {
            tested.state
                .onEach { println(it) }
                .toList(stateCollectionList)
        }
}