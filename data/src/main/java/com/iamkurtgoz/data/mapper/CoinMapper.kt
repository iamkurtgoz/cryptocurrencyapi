package com.iamkurtgoz.data.mapper

import com.iamkurtgoz.data.model.response.CoinData
import com.iamkurtgoz.domain.model.CoinUIModel
import com.iamkurtgoz.local.entity.CoinEntity
import java.util.UUID
import javax.inject.Inject

internal class CoinMapper @Inject constructor() {

    fun map(response: List<CoinData>): List<CoinUIModel> = response.map { data ->
        with(data) {
            CoinUIModel(
                id = this.id,
                symbol = this.symbol,
                name = this.name,
                image = this.image,
                currentPrice = this.currentPrice,
                marketCap = this.marketCap,
                totalVolume = this.totalVolume,
                high24h = this.high24h,
                low24h = this.low24h,
                priceChange24h = this.priceChange24h,
                priceChangePercentage24h = this.priceChangePercentage24h,
                marketCapChange24h = this.marketCapChange24h,
                marketCapChangePercentage24h = this.marketCapChangePercentage24h
            )
        }
    }

    fun mapUiModelToEntity(response: List<CoinUIModel>): List<CoinEntity> = response.map { data ->
        with(data) {
            CoinEntity(
                id = this.id ?: UUID.randomUUID().toString(),
                symbol = this.symbol,
                name = this.name,
                image = this.image,
                currentPrice = this.currentPrice,
                marketCap = this.marketCap,
                totalVolume = this.totalVolume,
                high24h = this.high24h,
                low24h = this.low24h,
                priceChange24h = this.priceChange24h,
                priceChangePercentage24h = this.priceChangePercentage24h,
                marketCapChange24h = this.marketCapChange24h,
                marketCapChangePercentage24h = this.marketCapChangePercentage24h
            )
        }
    }

    fun mapEntityToUiModel(response: List<CoinEntity>): List<CoinUIModel> = response.map { data ->
        with(data) {
            CoinUIModel(
                id = this.id,
                symbol = this.symbol,
                name = this.name,
                image = this.image,
                currentPrice = this.currentPrice,
                marketCap = this.marketCap,
                totalVolume = this.totalVolume,
                high24h = this.high24h,
                low24h = this.low24h,
                priceChange24h = this.priceChange24h,
                priceChangePercentage24h = this.priceChangePercentage24h,
                marketCapChange24h = this.marketCapChange24h,
                marketCapChangePercentage24h = this.marketCapChangePercentage24h
            )
        }
    }
}
