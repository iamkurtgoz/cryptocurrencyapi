package com.iamkurtgoz.domain.usecase

import com.iamkurtgoz.domain.model.CoinDetailUIModel
import com.iamkurtgoz.domain.model.FavoriteUIModel
import com.iamkurtgoz.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(): Flow<List<FavoriteUIModel>> = flow {
        emit(repository.getFavorites())
    }
}