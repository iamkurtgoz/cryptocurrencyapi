package com.iamkurtgoz.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iamkurtgoz.local.dao.CoinDao
import com.iamkurtgoz.local.dao.CoinDetailDao
import com.iamkurtgoz.local.entity.CoinDetailEntity
import com.iamkurtgoz.local.entity.CoinEntity

@Database(entities = [CoinEntity::class, CoinDetailEntity::class], version = 1)
internal abstract class CoinLocalDatabase : RoomDatabase() {

    abstract fun getCoinDao(): CoinDao

    abstract fun getCoinDetailDao(): CoinDetailDao
}
