package com.iamkurtgoz.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TableCoinEntity")
data class CoinEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "symbol")
    val symbol: String?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "currentPrice")
    val currentPrice: String?,

    @ColumnInfo(name = "marketCap")
    val marketCap: String?,

    @ColumnInfo(name = "totalVolume")
    val totalVolume: String?,

    @ColumnInfo(name = "high24h")
    val high24h: String?,

    @ColumnInfo(name = "low24h")
    val low24h: String?,

    @ColumnInfo(name = "priceChange24h")
    val priceChange24h: Double?,

    @ColumnInfo(name = "priceChangePercentage24h")
    val priceChangePercentage24h: Double?,

    @ColumnInfo(name = "marketCapChange24h")
    val marketCapChange24h: String?,

    @ColumnInfo(name = "marketCapChangePercentage24h")
    val marketCapChangePercentage24h: Double?,
)














