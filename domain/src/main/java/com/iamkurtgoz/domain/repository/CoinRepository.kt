package com.iamkurtgoz.domain.repository

import com.iamkurtgoz.domain.model.CoinDetailUIModel
import com.iamkurtgoz.domain.model.CoinUIModel
import com.iamkurtgoz.domain.model.FavoriteUIModel

interface CoinRepository {

    suspend fun getCoinList(
        vsCurrency: String?,
        page: Int?,
        perPage: Int?,
        ids: List<String>?
    ): List<CoinUIModel>

    suspend fun getCoinDetail(id: String?, withCache: Boolean): CoinDetailUIModel

    suspend fun getFavorites(): List<FavoriteUIModel>

    suspend fun addFavorites(id: String?): List<FavoriteUIModel>

    suspend fun removeFavorites(id: String?, documentId: String?): List<FavoriteUIModel>
}
