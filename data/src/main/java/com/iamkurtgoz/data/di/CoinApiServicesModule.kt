package com.iamkurtgoz.data.di

import com.iamkurtgoz.data.network.CoinApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object CoinApiServicesModule {

    @Provides
    fun provideCoinApiService(retrofit: Retrofit): CoinApiService = retrofit.create()
}
