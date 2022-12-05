package dev.ferrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dev.ferrant.ui.CryptoMarketApp
import dev.ferrant.ui.theme.CryptoMarketTheme
import dev.ferrant.util.CoinRefresher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var coinRefresher: CoinRefresher


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(coinRefresher)
        setContent {
            CryptoMarketTheme {
                CryptoMarketApp(inputEventFlow = errorStateFlow)
            }
        }
    }
}
