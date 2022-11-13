package dev.ferrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.ferrant.ui.CryptoMarketApp
import dev.ferrant.ui.theme.CryptoMarketTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoMarketTheme {
                CryptoMarketApp()
            }
        }
    }
}
