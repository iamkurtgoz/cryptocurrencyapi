package com.iamkurtgoz.presentation.core.extensions

import com.iamkurtgoz.contract.model.ErrorNetworkException
import com.iamkurtgoz.contract.model.RestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T> Flow<RestResult<T>>.onSuccess(action: (T) -> Unit): Flow<RestResult<T>> {
    return transform { restResult ->
        if (restResult is RestResult.Success) {
            action.invoke(restResult.result)
        }
        emit(restResult)
    }
}

fun <T> Flow<RestResult<T>>.onError(action: (ErrorNetworkException) -> Unit): Flow<RestResult<T>> {
    return transform { restResult ->
        if (restResult is RestResult.Error) {
            action.invoke(restResult.error)
        }
        emit(restResult)
    }
}

fun <T> Flow<RestResult<T>>.onLoading(action: () -> Unit): Flow<RestResult<T>> {
    return transform { restResult ->
        if (restResult is RestResult.Loading) {
            action.invoke()
        }
        emit(restResult)
    }
}
