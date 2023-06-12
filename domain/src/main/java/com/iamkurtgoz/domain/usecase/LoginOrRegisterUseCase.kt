package com.iamkurtgoz.domain.usecase

import com.iamkurtgoz.domain.repository.AuthRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class LoginOrRegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    operator fun invoke(email: String?, password: String?): Flow<Boolean> = channelFlow {
        val login = async { repository.loginOrRegister(email, password) }
        send(login.await())
    }
}