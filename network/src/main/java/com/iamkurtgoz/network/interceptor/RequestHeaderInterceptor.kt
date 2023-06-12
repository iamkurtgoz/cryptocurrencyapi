package com.iamkurtgoz.network.interceptor

import com.iamkurtgoz.contract.qualifiers.DeviceId
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class RequestHeaderInterceptor @Inject constructor(
    @DeviceId val deviceId: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val initial = chain.request()
        val builder = initial.newBuilder()
        val request = builder
            // .addHeader("deviceToken", deviceId)
            // .addHeader("operationSystem", "Android")
            // .addHeader("language", "en")
            .build()
        return chain.proceed(request)
    }
}
