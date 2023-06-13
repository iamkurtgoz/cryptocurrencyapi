package com.iamkurtgoz.data.repository

import com.iamkurtgoz.data.core.CoreRepository
import com.iamkurtgoz.domain.repository.AuthRepository
import com.iamkurtgoz.firebase.FirebaseInitializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val firebase: FirebaseInitializer
) : AuthRepository, CoreRepository() {

    override suspend fun isLogin(): Boolean {
        return firebase.auth.currentUser != null
    }

    override suspend fun loginOrRegister(email: String, password: String): Flow<Boolean> {
        return firebase.loginOrRegister(email, password).map { it != null }
    }

}