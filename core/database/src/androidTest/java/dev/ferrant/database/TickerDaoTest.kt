package dev.ferrant.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.ferrant.database.dao.TickerDao
import dev.ferrant.database.model.TickerEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.Instant

@OptIn(ExperimentalCoroutinesApi::class)
class TickerDaoTest {

    private lateinit var tickerDao: TickerDao
    private lateinit var db: CmDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            CmDatabase::class.java,
        ).build()
        tickerDao = db.tickerDao()
    }

    @Test
    fun tickerDao_filters_items_by_symbol() = runTest {
        val symbol = "tBTCUSD"

        val tickerEntities = listOf(
            testTicker(symbol),
            testTicker("tETHUSD"),
            testTicker("tLUNA:USD"),
            testTicker("tSOLUSD"),
            testTicker("tDOGE:USD"),
        )

        tickerDao.insertTickers(tickerEntities)
        val filteredTicker = tickerDao.getTickers(symbol = "%btc%").first()

        assertEquals(filteredTicker[0].symbol, symbol)
    }

}

private fun testTicker(symbol: String) = TickerEntity(
    symbol = symbol,
    bid = 0f,
    bidSize = 0f,
    ask = 0f,
    askSize = 0f,
    dailyChange = 0f,
    dailyChangeRelative = 0f,
    lastPrice = 0f,
    volume = 0f,
    high = 0f,
    low = 0f,
    date = Instant.now().toEpochMilli(),
)
