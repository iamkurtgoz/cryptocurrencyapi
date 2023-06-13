package com.iamkurtgoz.data.network

import com.iamkurtgoz.data.model.response.CoinData
import com.iamkurtgoz.data.model.response.coinDetail.CoinDetailData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CoinApiService {

    @GET("coins/markets?order=id_asc")
    suspend fun getCoinList(
        @Query("vs_currency") vsCurrency: String?,
        @Query("per_page") perPage: Int?,
        @Query("page") page: Int?
    ): List<CoinData>



    @GET("coins/{id}")
    suspend fun getCoinDetail(@Path("id") id: String?): CoinDetailData
}
