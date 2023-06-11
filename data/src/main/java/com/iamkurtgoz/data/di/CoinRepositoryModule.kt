package com.iamkurtgoz.data.di

import com.iamkurtgoz.data.mapper.CoinMapper
import com.iamkurtgoz.data.network.CoinApiService
import com.iamkurtgoz.data.repository.CoinRepositoryImpl
import com.iamkurtgoz.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface CoinRepositoryModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository
}
