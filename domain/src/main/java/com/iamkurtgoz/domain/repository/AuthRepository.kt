package com.iamkurtgoz.domain.repository

import com.iamkurtgoz.contract.model.RestResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun isLogin(): Boolean

    suspend fun loginOrRegister(email: String, password: String): Flow<Boolean>

}