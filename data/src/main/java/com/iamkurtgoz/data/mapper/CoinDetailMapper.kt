package com.iamkurtgoz.data.mapper

import com.iamkurtgoz.data.model.response.coinDetail.CoinDetailData
import com.iamkurtgoz.domain.model.CoinDetailUIModel
import com.iamkurtgoz.local.entity.CoinDetailEntity
import java.util.UUID
import javax.inject.Inject

internal class CoinDetailMapper @Inject constructor() {

    fun map(response: CoinDetailData): CoinDetailUIModel {
        with(response) {
            return CoinDetailUIModel(
                circulatingSupply = this.marketData?.circulatingSupply,
                currentPrice = this.marketData?.coinPriceData?.tryX,
                description = this.descriptionData?.en,
                genesisDate = this.genesisDate,
                hashingAlgorithm = this.hashingAlgorithm,
                high24h = this.marketData?.high24h?.tryX,
                id = this.id,
                image = this.imageData?.large,
                lastUpdated = this.lastUpdated,
                low24h = this.marketData?.low24h?.tryX,
                marketCapChange24h = this.marketData?.marketCapChange24h,
                marketCapChangePercentage24h = this.marketData?.marketCapChangePercentage24h,
                maxSupply = this.marketData?.maxSupply,
                name = this.name,
                priceChange24h = this.marketData?.priceChange24h,
                priceChange24hInCurrency = this.marketData?.priceChange24hInCurrency?.tryX,
                priceChangePercentage14d = this.marketData?.priceChangePercentage14d,
                priceChangePercentage14dInCurrency = this.marketData?.priceChangePercentage14dInCurrency?.tryX,
                priceChangePercentage1hInCurrency = this.marketData?.priceChangePercentage1hInCurrency?.tryX,
                priceChangePercentage1y = this.marketData?.priceChangePercentage1y,
                priceChangePercentage1yInCurrency = this.marketData?.priceChangePercentage1yInCurrency?.tryX,
                priceChangePercentage200d = this.marketData?.priceChangePercentage200d,
                priceChangePercentage200dInCurrency = this.marketData?.priceChangePercentage200dInCurrency?.tryX,
                priceChangePercentage24h = this.marketData?.priceChangePercentage24h,
                priceChangePercentage24hInCurrency = this.marketData?.priceChangePercentage24hInCurrency?.tryX,
                priceChangePercentage30d = this.marketData?.priceChangePercentage30d,
                priceChangePercentage30dInCurrency = this.marketData?.priceChangePercentage30dInCurrency?.tryX,
                priceChangePercentage60d = this.marketData?.priceChangePercentage60d,
                priceChangePercentage60dInCurrency = this.marketData?.priceChangePercentage60dInCurrency?.tryX,
                priceChangePercentage7d = this.marketData?.priceChangePercentage7d,
                priceChangePercentage7dInCurrency = this.marketData?.priceChangePercentage7dInCurrency?.tryX,
                symbol = this.symbol,
                totalSupply = this.marketData?.totalSupply
            )
        }
    }

    fun mapUiModelToEntity(response: CoinDetailUIModel): CoinDetailEntity {
        with(response) {
            return CoinDetailEntity(
                circulatingSupply = circulatingSupply,
                currentPrice = currentPrice,
                description = description,
                genesisDate = genesisDate,
                hashingAlgorithm = hashingAlgorithm,
                high24h = high24h,
                id = id ?: UUID.randomUUID().toString(),
                image = image,
                lastUpdated = lastUpdated,
                low24h = low24h,
                marketCapChange24h = marketCapChange24h,
                marketCapChangePercentage24h = marketCapChangePercentage24h,
                maxSupply = maxSupply,
                name = name,
                priceChange24h = priceChange24h,
                priceChange24hInCurrency = priceChange24hInCurrency,
                priceChangePercentage14d = priceChangePercentage14d,
                priceChangePercentage14dInCurrency = priceChangePercentage14dInCurrency,
                priceChangePercentage1hInCurrency = priceChangePercentage1hInCurrency,
                priceChangePercentage1y = priceChangePercentage1y,
                priceChangePercentage1yInCurrency = priceChangePercentage1yInCurrency,
                priceChangePercentage200d = priceChangePercentage200d,
                priceChangePercentage200dInCurrency = priceChangePercentage200dInCurrency,
                priceChangePercentage24h = priceChangePercentage24h,
                priceChangePercentage24hInCurrency = priceChangePercentage24hInCurrency,
                priceChangePercentage30d = priceChangePercentage30d,
                priceChangePercentage30dInCurrency = priceChangePercentage30dInCurrency,
                priceChangePercentage60d = priceChangePercentage60d,
                priceChangePercentage60dInCurrency = priceChangePercentage60dInCurrency,
                priceChangePercentage7d = priceChangePercentage7d,
                priceChangePercentage7dInCurrency = priceChangePercentage7dInCurrency,
                symbol = symbol,
                totalSupply = totalSupply
            )
        }
    }

    fun mapEntityToUiModel(response: CoinDetailEntity): CoinDetailUIModel {
        with(response) {
            return CoinDetailUIModel(
                circulatingSupply = circulatingSupply,
                currentPrice = currentPrice,
                description = description,
                genesisDate = genesisDate,
                hashingAlgorithm = hashingAlgorithm,
                high24h = high24h,
                id = id,
                image = image,
                lastUpdated = lastUpdated,
                low24h = low24h,
                marketCapChange24h = marketCapChange24h,
                marketCapChangePercentage24h = marketCapChangePercentage24h,
                maxSupply = maxSupply,
                name = name,
                priceChange24h = priceChange24h,
                priceChange24hInCurrency = priceChange24hInCurrency,
                priceChangePercentage14d = priceChangePercentage14d,
                priceChangePercentage14dInCurrency = priceChangePercentage14dInCurrency,
                priceChangePercentage1hInCurrency = priceChangePercentage1hInCurrency,
                priceChangePercentage1y = priceChangePercentage1y,
                priceChangePercentage1yInCurrency = priceChangePercentage1yInCurrency,
                priceChangePercentage200d = priceChangePercentage200d,
                priceChangePercentage200dInCurrency = priceChangePercentage200dInCurrency,
                priceChangePercentage24h = priceChangePercentage24h,
                priceChangePercentage24hInCurrency = priceChangePercentage24hInCurrency,
                priceChangePercentage30d = priceChangePercentage30d,
                priceChangePercentage30dInCurrency = priceChangePercentage30dInCurrency,
                priceChangePercentage60d = priceChangePercentage60d,
                priceChangePercentage60dInCurrency = priceChangePercentage60dInCurrency,
                priceChangePercentage7d = priceChangePercentage7d,
                priceChangePercentage7dInCurrency = priceChangePercentage7dInCurrency,
                symbol = symbol,
                totalSupply = totalSupply
            )
        }
    }
}
