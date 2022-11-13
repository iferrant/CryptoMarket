package dev.ferrant.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.ferrant.database.dao.TickerDao
import dev.ferrant.database.model.TickerEntity
import dev.ferrant.database.util.TimestampConverter

@Database(
    entities = [
        TickerEntity::class,
    ],
    version = 1,
)
@TypeConverters(TimestampConverter::class)
abstract class CmDatabase : RoomDatabase() {
    abstract fun tickerDao(): TickerDao
}