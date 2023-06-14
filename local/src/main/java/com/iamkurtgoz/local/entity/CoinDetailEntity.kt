package com.iamkurtgoz.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TableCoinDetailEntity")
data class CoinDetailEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "circulatingSupply")
    val circulatingSupply: String?,

    @ColumnInfo(name = "currentPrice")
    val currentPrice: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "genesisDate")
    val genesisDate: String?,

    @ColumnInfo(name = "hashingAlgorithm")
    val hashingAlgorithm: String?,

    @ColumnInfo(name = "high24h")
    val high24h: String?,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "lastUpdated")
    val lastUpdated: String?,

    @ColumnInfo(name = "low24h")
    val low24h: String?,

    @ColumnInfo(name = "marketCapChange24h")
    val marketCapChange24h: String?,

    @ColumnInfo(name = "marketCapChangePercentage24h")
    val marketCapChangePercentage24h: String?,

    @ColumnInfo(name = "maxSupply")
    val maxSupply: String?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "priceChange24h")
    val priceChange24h: String?,

    @ColumnInfo(name = "priceChange24hInCurrency")
    val priceChange24hInCurrency: String?,

    @ColumnInfo(name = "priceChangePercentage14d")
    val priceChangePercentage14d: String?,

    @ColumnInfo(name = "priceChangePercentage14dInCurrency")
    val priceChangePercentage14dInCurrency: String?,

    @ColumnInfo(name = "priceChangePercentage1hInCurrency")
    val priceChangePercentage1hInCurrency: String?,

    @ColumnInfo(name = "priceChangePercentage1y")
    val priceChangePercentage1y: String?,

    @ColumnInfo(name = "priceChangePercentage1yInCurrency")
    val priceChangePercentage1yInCurrency: String?,

    @ColumnInfo(name = "priceChangePercentage200d")
    val priceChangePercentage200d: String?,

    @ColumnInfo(name = "priceChangePercentage200dInCurrency")
    val priceChangePercentage200dInCurrency: String?,

    @ColumnInfo(name = "priceChangePercentage24h")
    val priceChangePercentage24h: String?,

    @ColumnInfo(name = "priceChangePercentage24hInCurrency")
    val priceChangePercentage24hInCurrency: String?,

    @ColumnInfo(name = "priceChangePercentage30d")
    val priceChangePercentage30d: String?,

    @ColumnInfo(name = "priceChangePercentage30dInCurrency")
    val priceChangePercentage30dInCurrency: String?,

    @ColumnInfo(name = "priceChangePercentage60d")
    val priceChangePercentage60d: String?,

    @ColumnInfo(name = "priceChangePercentage60dInCurrency")
    val priceChangePercentage60dInCurrency: String?,

    @ColumnInfo(name = "priceChangePercentage7d")
    val priceChangePercentage7d: String?,

    @ColumnInfo(name = "priceChangePercentage7dInCurrency")
    val priceChangePercentage7dInCurrency: String?,

    @ColumnInfo(name = "symbol")
    val symbol: String?,

    @ColumnInfo(name = "totalSupply")
    val totalSupply: String?
)
