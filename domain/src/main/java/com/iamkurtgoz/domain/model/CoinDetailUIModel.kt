package com.iamkurtgoz.domain.model

data class CoinDetailUIModel(
    val circulatingSupply: String?,
    val currentPrice: String?,
    val description: String?,
    val genesisDate: String?,
    val hashingAlgorithm: String?,
    val high24h: String?,
    val id: String?,
    val image: String?,
    val lastUpdated: String?,
    val low24h: String?,
    val marketCapChange24h: String?,
    val marketCapChangePercentage24h: String?,
    val maxSupply: String?,
    val name: String?,
    val priceChange24h: String?,
    val priceChange24hInCurrency: String?,
    val priceChangePercentage14d: String?,
    val priceChangePercentage14dInCurrency: String?,
    val priceChangePercentage1hInCurrency: String?,
    val priceChangePercentage1y: String?,
    val priceChangePercentage1yInCurrency: String?,
    val priceChangePercentage200d: String?,
    val priceChangePercentage200dInCurrency: String?,
    val priceChangePercentage24h: String?,
    val priceChangePercentage24hInCurrency: String?,
    val priceChangePercentage30d: String?,
    val priceChangePercentage30dInCurrency: String?,
    val priceChangePercentage60d: String?,
    val priceChangePercentage60dInCurrency: String?,
    val priceChangePercentage7d: String?,
    val priceChangePercentage7dInCurrency: String?,
    val symbol: String?,
    val totalSupply: String?
) {
    val isPricePercentage24hUpper: Boolean
        get() = (priceChangePercentage24h?.toDoubleOrNull() ?: 0.0) > 0
}
