package com.iamkurtgoz.data.model.response.coinDetail


import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("large")
    val large: String?,
    @SerializedName("small")
    val small: String?,
    @SerializedName("thumb")
    val thumb: String?
)