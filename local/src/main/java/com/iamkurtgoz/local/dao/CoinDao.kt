package com.iamkurtgoz.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iamkurtgoz.local.entity.CoinEntity

@Dao
interface CoinDao {

    @Query("SELECT * FROM TableCoinEntity WHERE `id`=:id")
    fun getCoinById(id: String): CoinEntity?

    @Query("SELECT * FROM TableCoinEntity ORDER BY id LIMIT :limit OFFSET :offset")
    fun getCoins(limit: Int?, offset: Int?): List<CoinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(services: List<CoinEntity>)
}
