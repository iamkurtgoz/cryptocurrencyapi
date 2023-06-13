package com.iamkurtgoz.domain.usecase

import com.iamkurtgoz.domain.model.FavoriteUIModel
import com.iamkurtgoz.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteFavoritesUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(id: String?, documentId: String?): Flow<List<FavoriteUIModel>> = flow {
        emit(repository.removeFavorites(id, documentId))
    }
}