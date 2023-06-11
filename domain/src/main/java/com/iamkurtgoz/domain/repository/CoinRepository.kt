package com.iamkurtgoz.domain.repository

import com.iamkurtgoz.domain.model.CoinUIModel

interface CoinRepository {

    suspend fun getCoinList(
        vsCurrency: String?,
        page: Int?,
        perPage: Int?
    ): List<CoinUIModel>

}
