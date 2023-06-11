package com.iamkurtgoz.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GsonModule {

    private const val WITH_TIME_ZONE = "yyyy-MM-dd'T'HH:mm:ss.SSS"

    @Provides
    fun buildWithTimeZone(): Gson {
        return GsonBuilder()
            .setLenient()
            .setPrettyPrinting()
            .setDateFormat(WITH_TIME_ZONE)
            .create()
    }
}
