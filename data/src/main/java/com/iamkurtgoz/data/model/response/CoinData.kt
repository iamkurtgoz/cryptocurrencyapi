package com.iamkurtgoz.data.model.response

import com.google.gson.annotations.SerializedName

data class CoinData(
    @SerializedName("ath")
    val ath: String?,
    @SerializedName("ath_change_percentage")
    val athChangePercentage: Double?,
    @SerializedName("ath_date")
    val athDate: String?,
    @SerializedName("atl")
    val atl: Double?,
    @SerializedName("atl_change_percentage")
    val atlChangePercentage: Double?,
    @SerializedName("atl_date")
    val atlDate: String?,
    @SerializedName("circulating_supply")
    val circulatingSupply: String?,
    @SerializedName("current_price")
    val currentPrice: String?,
    @SerializedName("fully_diluted_valuation")
    val fullyDilutedValuation: Long?,
    @SerializedName("high_24h")
    val high24h: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("low_24h")
    val low24h: String?,
    @SerializedName("market_cap")
    val marketCap: String?,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: String?,
    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double?,
    @SerializedName("market_cap_rank")
    val marketCapRank: String?,
    @SerializedName("max_supply")
    val maxSupply: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price_change_24h")
    val priceChange24h: Double?,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double?,
    @SerializedName("roi")
    val roi: Any?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("total_supply")
    val totalSupply: String?,
    @SerializedName("total_volume")
    val totalVolume: String?
)
