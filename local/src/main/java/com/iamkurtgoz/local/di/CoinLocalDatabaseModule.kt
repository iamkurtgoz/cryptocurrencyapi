package com.iamkurtgoz.local.di

import android.content.Context
import androidx.room.Room
import com.iamkurtgoz.local.CoinLocalDatabase
import com.iamkurtgoz.local.qualifier.DatabaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class CoinLocalDatabaseModule {

    @Provides
    fun provideCoinLocalDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): CoinLocalDatabase {
        return Room.databaseBuilder(
            context,
            CoinLocalDatabase::class.java,
            databaseName
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideCoinDao(db: CoinLocalDatabase) = db.getCoinDao()
}
