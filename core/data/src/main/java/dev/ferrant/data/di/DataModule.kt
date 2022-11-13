package dev.ferrant.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ferrant.data.repository.TickerRepository
import dev.ferrant.data.repository.TickerRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsTickerRepository(
        tickerRepositoryImpl: TickerRepositoryImpl,
    ): TickerRepository
}
