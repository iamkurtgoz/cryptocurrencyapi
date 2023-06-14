package com.iamkurtgoz.domain.usecase

import com.iamkurtgoz.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginOrRegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Flow<Boolean> {
        return repository.loginOrRegister(email, password)
    }
}
