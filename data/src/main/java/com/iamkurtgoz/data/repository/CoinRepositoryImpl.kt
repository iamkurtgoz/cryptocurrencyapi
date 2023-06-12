package com.iamkurtgoz.data.repository

import androidx.datastore.preferences.core.stringPreferencesKey
import com.iamkurtgoz.data.core.CoreRepository
import com.iamkurtgoz.data.local.UserDataStoreManager
import com.iamkurtgoz.data.mapper.CoinMapper
import com.iamkurtgoz.data.network.CoinApiService
import com.iamkurtgoz.domain.model.CoinUIModel
import com.iamkurtgoz.domain.repository.CoinRepository
import com.iamkurtgoz.local.dao.CoinDao
import kotlinx.coroutines.flow.firstOrNull
import java.util.Date
import javax.inject.Inject

internal class CoinRepositoryImpl @Inject constructor(
    private val apiService: CoinApiService,
    private val coinDao: CoinDao,
    private val coinMapper: CoinMapper,
    private val userDataStoreManager: UserDataStoreManager
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
}
