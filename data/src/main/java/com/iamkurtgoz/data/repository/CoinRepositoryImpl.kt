package com.iamkurtgoz.data.repository

import androidx.datastore.preferences.core.stringPreferencesKey
import com.iamkurtgoz.data.core.CoreRepository
import com.iamkurtgoz.data.local.UserDataStoreManager
import com.iamkurtgoz.data.mapper.CoinDetailMapper
import com.iamkurtgoz.data.mapper.CoinMapper
import com.iamkurtgoz.data.mapper.FavoriteMapper
import com.iamkurtgoz.data.network.CoinApiService
import com.iamkurtgoz.domain.model.CoinDetailUIModel
import com.iamkurtgoz.domain.model.CoinUIModel
import com.iamkurtgoz.domain.model.FavoriteUIModel
import com.iamkurtgoz.domain.repository.CoinRepository
import com.iamkurtgoz.firebase.FirebaseInitializer
import com.iamkurtgoz.local.dao.CoinDao
import com.iamkurtgoz.local.dao.CoinDetailDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

internal class CoinRepositoryImpl @Inject constructor(
    private val apiService: CoinApiService,
    private val firebase: FirebaseInitializer,
    private val coinDao: CoinDao,
    private val coinDetailDao: CoinDetailDao,
    private val coinMapper: CoinMapper,
    private val coinDetailMapper: CoinDetailMapper,
    private val userDataStoreManager: UserDataStoreManager,
    private val favoriteMapper: FavoriteMapper
) : CoinRepository, CoreRepository() {

    override suspend fun getCoinList(
        vsCurrency: String?,
        page: Int?,
        perPage: Int?
    ): List<CoinUIModel> {
        val keyName = "CACHE_$page"
        val key = stringPreferencesKey(keyName)
        val cache = userDataStoreManager.read(key).firstOrNull()
        val fiveMin = (1000 * 60) * 5
        var useCache = false
        page?.toString()?.let { safePage ->
            val lastUpdate = cache?.toLongOrNull()
            if (lastUpdate != null && (Date().time - lastUpdate) <= fiveMin) {
                useCache = true
            }
        }

        //TODO: check network status
        return if (useCache) {
            val offset = (page ?: 1).minus(1) * (perPage ?: 250)
            coinMapper.mapEntityToUiModel(coinDao.getCoins(limit = perPage, offset = offset))
        } else {
            // Save cache time
            userDataStoreManager.save(key, Date().time.toString())
            // uiList
            val list = apiService.getCoinList(vsCurrency, perPage, page)
            val uiList = coinMapper.map(list)
            val entityList = coinMapper.mapUiModelToEntity(uiList)
            coinDao.insertCoin(entityList)
            uiList
        }
    }

    override suspend fun getCoinDetail(id: String?, withCache: Boolean): CoinDetailUIModel {
        val keyName = "CACHE_$id"
        val key = stringPreferencesKey(keyName)
        val cache = userDataStoreManager.read(key).firstOrNull()
        val fiveMin = (1000 * 60) * 1
        var useCache = false
        id?.let {
            val lastUpdate = cache?.toLongOrNull()
            if (lastUpdate != null && (Date().time - lastUpdate) <= fiveMin) {
                useCache = true
            }
        }

        //TODO: check network status
        val cacheData = coinDetailDao.getCoinDetailById(id)
        return if (withCache && useCache && cacheData != null) {
            coinDetailMapper.mapEntityToUiModel(cacheData)
        } else {
            // Save cache time
            userDataStoreManager.save(key, Date().time.toString())
            // uiList
            val coinData = apiService.getCoinDetail(id)
            val uiData = coinDetailMapper.map(coinData)
            val entity = coinDetailMapper.mapUiModelToEntity(uiData)
            coinDetailDao.insertCoinDetail(entity)
            uiData
        }
    }

    override suspend fun getFavorites(): List<FavoriteUIModel> {
        val email = firebase.auth.currentUser?.email
        val favorites = firebase.readFavorites(email = email)
        return favoriteMapper.map(favorites)
    }

    override suspend fun addFavorites(id: String?): List<FavoriteUIModel> {
        val email = firebase.auth.currentUser?.email
        firebase.addFavorites(email, id)
        return getFavorites()
    }

    override suspend fun removeFavorites(id: String?, documentId: String?): List<FavoriteUIModel> {
        documentId?.let {
            firebase.removeFavorites(documentId)
        }
        return getFavorites()
    }
}
