package dev.ferrant.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


sealed class Screen(val route: String) {
    object Search : Screen("search")
}

@Composable
fun rememberCryptoMarketAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    CryptoMarketAppState(navController, context)
}

class CryptoMarketAppState(
    val navController: NavHostController,
    private val context: Context,
) {

    fun navigateBack() {
        navController.popBackStack()
    }

}
