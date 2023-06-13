package com.iamkurtgoz.domain.usecase

import com.iamkurtgoz.domain.repository.AuthRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginOrRegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Flow<Boolean> {
        return repository.loginOrRegister(email, password)
    }
}