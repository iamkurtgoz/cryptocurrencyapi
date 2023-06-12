package com.iamkurtgoz.data.di

import com.iamkurtgoz.local.qualifier.DatabaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface CoinDatabaseModule {

    companion object {

        @DatabaseName
        @Provides
        fun provideDatabaseName() = "CoinLocalDatabase"
    }
}
