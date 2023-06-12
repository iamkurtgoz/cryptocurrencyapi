package com.iamkurtgoz.domain.repository

import com.iamkurtgoz.contract.model.RestResult

interface AuthRepository {

    suspend fun isLogin(): Boolean

    suspend fun loginOrRegister(email: String?, password: String?): Boolean

}