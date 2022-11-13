package dev.ferrant.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.ferrant.database.model.TickerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TickerDao {
    @Query(
        value = """
            SELECT * FROM tickers
            WHERE symbol LIKE :symbol
        """
    )
    fun getTickers(symbol: String): Flow<List<TickerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTickers(tickerEntities: List<TickerEntity>)
}
