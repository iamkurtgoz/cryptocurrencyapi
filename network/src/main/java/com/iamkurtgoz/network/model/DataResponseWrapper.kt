package com.iamkurtgoz.network.model

import com.google.gson.annotations.SerializedName

data class DataResponseWrapper<out T>(
    @SerializedName("data")
    val data: T? = null,

    @SerializedName("status")
    val status: Boolean = false,

    @SerializedName("message")
    val message: String? = null
)
