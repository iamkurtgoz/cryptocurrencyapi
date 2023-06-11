package com.iamkurtgoz.contract.model

data class ErrorNetworkException(
    val errorMessage: String,
    val errorCode: Int
) : Exception()
