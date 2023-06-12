package com.iamkurtgoz.data.di

import com.iamkurtgoz.data.repository.AuthRepositoryImpl
import com.iamkurtgoz.data.repository.CoinRepositoryImpl
import com.iamkurtgoz.domain.repository.AuthRepository
import com.iamkurtgoz.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface CoinRepositoryModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
