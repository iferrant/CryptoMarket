package dev.ferrant.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ferrant.database.CmDatabase
import dev.ferrant.database.dao.TickerDao

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideTickerDao(
        database: CmDatabase,
    ): TickerDao = database.tickerDao()
}
