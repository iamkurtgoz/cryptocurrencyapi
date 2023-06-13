package com.iamkurtgoz.data.model.response.coinDetail


import com.google.gson.annotations.SerializedName

data class MarketData(
    @SerializedName("circulating_supply")
    val circulatingSupply: String?,
    @SerializedName("current_price")
    val coinPriceData: CoinPriceData?,
    @SerializedName("high_24h")
    val high24h: CoinPriceData?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("low_24h")
    val low24h: CoinPriceData?,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: String?,
    @SerializedName("market_cap_change_24h_in_currency")
    val marketCapChange24hInCurrency: CoinPriceData?,
    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: String?,
    @SerializedName("market_cap_change_percentage_24h_in_currency")
    val marketCapChangePercentage24hInCurrency: CoinPriceData?,
    @SerializedName("max_supply")
    val maxSupply: String?,
    @SerializedName("price_change_24h")
    val priceChange24h: String?,
    @SerializedName("price_change_24h_in_currency")
    val priceChange24hInCurrency: CoinPriceData?,
    @SerializedName("price_change_percentage_14d")
    val priceChangePercentage14d: String?,
    @SerializedName("price_change_percentage_14d_in_currency")
    val priceChangePercentage14dInCurrency: CoinPriceData?,
    @SerializedName("price_change_percentage_1h_in_currency")
    val priceChangePercentage1hInCurrency: CoinPriceData?,
    @SerializedName("price_change_percentage_1y")
    val priceChangePercentage1y: String?,
    @SerializedName("price_change_percentage_1y_in_currency")
    val priceChangePercentage1yInCurrency: CoinPriceData?,
    @SerializedName("price_change_percentage_200d")
    val priceChangePercentage200d: String?,
    @SerializedName("price_change_percentage_200d_in_currency")
    val priceChangePercentage200dInCurrency: CoinPriceData?,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: String?,
    @SerializedName("price_change_percentage_24h_in_currency")
    val priceChangePercentage24hInCurrency: CoinPriceData?,
    @SerializedName("price_change_percentage_30d")
    val priceChangePercentage30d: String?,
    @SerializedName("price_change_percentage_30d_in_currency")
    val priceChangePercentage30dInCurrency: CoinPriceData?,
    @SerializedName("price_change_percentage_60d")
    val priceChangePercentage60d: String?,
    @SerializedName("price_change_percentage_60d_in_currency")
    val priceChangePercentage60dInCurrency: CoinPriceData?,
    @SerializedName("price_change_percentage_7d")
    val priceChangePercentage7d: String?,
    @SerializedName("price_change_percentage_7d_in_currency")
    val priceChangePercentage7dInCurrency: CoinPriceData?,
    @SerializedName("total_supply")
    val totalSupply: String?
)