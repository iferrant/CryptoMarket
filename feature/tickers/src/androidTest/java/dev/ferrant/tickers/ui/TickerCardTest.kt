package dev.ferrant.tickers.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.ferrant.model.Ticker
import dev.ferrant.tickers.contract.TickerListItem
import org.junit.Rule
import org.junit.Test
import java.util.*

class TickerCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displaysTickerInformation() {
        with(composeTestRule) {
            setContent { TickerCard(item = testTickerItem) }
            onNodeWithText("BTC").assertIsDisplayed()
            onNodeWithText("/USD").assertIsDisplayed()
            onNodeWithText("17784.0").assertIsDisplayed()
            onNodeWithText("2.74%").assertIsDisplayed()
        }
    }

}

private val testTickerItem = TickerListItem.TickerItem(
    ticker = Ticker(
        symbol = "tBTCUSD",
        bid = 17783f,
        bidSize = 49.24085298f,
        ask = 17784f,
        askSize = 179.50908914f,
        dailyChange = 474f,
        dailyChangeRelative = 0.0274f,
        lastPrice = 17784f,
        volume = 25373.17831895f,
        high = 17956f,
        low = 15588f,
        date = Calendar.getInstance().timeInMillis,
    )
)

