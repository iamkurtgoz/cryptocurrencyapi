package com.iamkurtgoz.contract.model

sealed class RestResult<out T> {

    class Success<out T>(
        val result: T
    ) : RestResult<T>()

    class Error(
        val error: ErrorNetworkException
    ) : RestResult<Nothing>()

    object Loading : RestResult<Nothing>()
}

inline fun <T> RestResult<T>.onSuccess(action: (T) -> Unit): RestResult<T> {
    if (this is RestResult.Success) action(result)
    return this
}

inline fun <T> RestResult<T>.onError(action: (exc: ErrorNetworkException) -> Unit): RestResult<T> {
    if (this is RestResult.Error) action(error)
    return this
}

inline fun <T> RestResult<T>.onLoading(action: () -> Unit): RestResult<T> {
    if (this is RestResult.Loading) action()
    return this
}

inline fun <T, R> RestResult<T>.mapOnSuccess(map: (T) -> R): RestResult<R> = when (this) {
    is RestResult.Success -> RestResult.Success(map(result))
    is RestResult.Error -> this
    is RestResult.Loading -> this
}
