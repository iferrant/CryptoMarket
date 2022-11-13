package dev.ferrant.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.ferrant.tickers.ui.TickersRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoMarketApp(
    appState: CryptoMarketAppState = rememberCryptoMarketAppState()
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.fillMaxSize()
    ) { padding->

        NavHost(
            navController = appState.navController,
            startDestination = Screen.Search.route,
            modifier = Modifier.padding(padding),
        ) {
            composable(Screen.Search.route) {
                TickersRoute()
            }
        }
    }
}