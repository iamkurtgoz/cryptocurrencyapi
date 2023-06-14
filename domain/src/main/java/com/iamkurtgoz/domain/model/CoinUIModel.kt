package com.iamkurtgoz.domain.model

import com.iamkurtgoz.domain.extensions.formatDecimal
import java.text.NumberFormat
import java.util.Locale

data class CoinUIModel(
    val id: String?,
    val symbol: String?,
    val name: String?,
    val image: String?,
    val currentPrice: String?,
    val marketCap: String?,
    val totalVolume: String?,
    val high24h: String?,
    val low24h: String?,
    val priceChange24h: Double?,
    val priceChangePercentage24h: Double?,
    val marketCapChange24h: String?,
    val marketCapChangePercentage24h: Double?
) {
    fun formatHigh24(): String {
        return high24h?.formatDecimal ?: kotlin.run {
            high24h?.let {
                "$high24h₺" ?: "--"
            } ?: kotlin.run {
                "-"
            }
        }
    }

    fun formatLow24(): String {
        return low24h?.formatDecimal ?: kotlin.run {
            low24h?.let {
                "$low24h₺" ?: "--"
            } ?: kotlin.run {
                "-"
            }
        }
    }

    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            id ?: "-",
            name ?: "-",
            symbol ?: "-"
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
