package com.iamkurtgoz.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.iamkurtgoz.local.entity.CoinDetailEntity

@Dao
interface CoinDetailDao {

    @Query("SELECT * FROM TableCoinDetailEntity WHERE `id`=:id")
    fun getCoinDetailById(id: String?): CoinDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinDetail(services: CoinDetailEntity)

    @Update
    fun updateCoin(coinDetail: CoinDetailEntity)
}
