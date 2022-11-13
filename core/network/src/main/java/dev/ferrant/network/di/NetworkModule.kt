package dev.ferrant.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ferrant.network.CmNetworkDatasource
import dev.ferrant.network.retrofit.CmNetworkDatasourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Binds
    fun bindsNetworkDatasource(networkDatasource: CmNetworkDatasourceImpl): CmNetworkDatasource
}
