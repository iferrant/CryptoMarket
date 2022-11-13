package dev.ferrant.network.retrofit

import dev.ferrant.network.BuildConfig
import dev.ferrant.network.CmNetworkDatasource
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
    @GET("tickers")
    suspend fun tickers(
        @Query("symbols") symbols: String,
    ): List<List<String>>
}

@Singleton
class CmNetworkDatasourceImpl @Inject constructor() : CmNetworkDatasource {

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

    override suspend fun tickers(symbols: String): List<NetworkTicker> =
        networkApi.tickers(symbols).map {
            NetworkTicker(
                symbol = it[0],
                bid = it[1].toFloat(),
                bidSize = it[2].toFloat(),
                ask = it[3].toFloat(),
                askSize = it[4].toFloat(),
                dailyChange = it[5].toFloat(),
                dailyChangeRelative = it[6].toFloat(),
                lastPrice = it[7].toFloat(),
                volume = it[8].toFloat(),
                high = it[9].toFloat(),
                low = it[10].toFloat(),
            )
        }
}