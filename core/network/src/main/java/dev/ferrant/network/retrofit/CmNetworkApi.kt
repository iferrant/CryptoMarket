package dev.ferrant.network.retrofit

import dev.ferrant.network.BuildConfig
import dev.ferrant.network.model.NetworkTicker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

interface CmNetworkApi {
    @GET
    suspend fun tickers(
        @Query("symbols") symbols: String,
    ): List<NetworkTicker>
}

@Singleton
class CmNetwork @Inject constructor() : CmNetworkApi {

    private val networkApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CmNetworkApi::class.java)

    override suspend fun tickers(symbols: String): List<NetworkTicker> {
        TODO("Not yet implemented")
    }
}