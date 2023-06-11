package com.iamkurtgoz.domain.model

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
        high24h?.toDoubleOrNull()?.let {
            return formatCurrency(it)
        }
        return "$high24h ₺" ?: "--"
    }

    fun formatLow24(): String {
        low24h?.toDoubleOrNull()?.let {
            return formatCurrency(it)
        }
        return "$low24h ₺" ?: "--"
    }

    fun formatCurrency(value: Double): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale("tr", "TR"))
        numberFormat.maximumFractionDigits = 2
        return "${numberFormat.format(value)} ₺"
    }

    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            id ?: "-",
            name ?: "-"
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
