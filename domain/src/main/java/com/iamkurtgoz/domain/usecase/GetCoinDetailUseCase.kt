package com.iamkurtgoz.domain.usecase

import com.iamkurtgoz.domain.model.CoinDetailUIModel
import com.iamkurtgoz.domain.repository.AuthRepository
import com.iamkurtgoz.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(id: String?, withCache: Boolean): Flow<CoinDetailUIModel?> = flow {
        val response = repository.getCoinDetail(id = id, withCache = withCache)
        emit(response)
    }
}