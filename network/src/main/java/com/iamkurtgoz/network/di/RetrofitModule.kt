package com.iamkurtgoz.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.iamkurtgoz.contract.qualifiers.DeviceId
import com.iamkurtgoz.contract.sensitive.SensitiveKeys
import com.iamkurtgoz.network.interceptor.RequestHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIMEOUT_MS = 60L

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        requestHeaderInterceptor: RequestHeaderInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_MS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_MS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_MS, TimeUnit.SECONDS)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(requestHeaderInterceptor)

        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .baseUrl(SensitiveKeys.baseAddress())
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideRequestHeaderInterceptor(@DeviceId deviceId: String): Interceptor {
        return RequestHeaderInterceptor(deviceId)
    }

    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }
}
