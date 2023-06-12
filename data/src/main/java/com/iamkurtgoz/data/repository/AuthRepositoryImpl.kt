package com.iamkurtgoz.data.repository

import com.iamkurtgoz.data.core.CoreRepository
import com.iamkurtgoz.domain.repository.AuthRepository
import com.iamkurtgoz.firebase.FirebaseInitializer
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val firebase: FirebaseInitializer
) : AuthRepository, CoreRepository() {

    override suspend fun isLogin(): Boolean {
        return firebase.auth.currentUser != null
    }

    override suspend fun loginOrRegister(email: String?, password: String?): Boolean {
        if (email != null && password != null) {
            return firebase.loginOrRegister(email, password) != null
        }
        return false
    }

}