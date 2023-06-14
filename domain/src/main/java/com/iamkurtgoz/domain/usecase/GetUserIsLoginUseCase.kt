package com.iamkurtgoz.domain.usecase

import com.iamkurtgoz.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserIsLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    private val ioDispatcher = Dispatchers.IO

    suspend operator fun invoke(): Boolean = withContext(ioDispatcher) {
        authRepository.isLogin()
    }
}
