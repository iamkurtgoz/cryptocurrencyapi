package com.iamkurtgoz.cryptocurrencyapi.di

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.iamkurtgoz.contract.qualifiers.DeviceId
import com.iamkurtgoz.presentation.core.SharedUserState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    companion object {

        @SuppressLint("HardwareIds")
        @DeviceId
        @Provides
        fun provideDeviceId(@ApplicationContext context: Context): String =
            Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )

        @Provides
        @Singleton
        fun provideSharedUserState(): SharedUserState = SharedUserState()
    }
}
