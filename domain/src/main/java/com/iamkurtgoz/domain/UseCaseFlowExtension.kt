package com.iamkurtgoz.domain

import com.iamkurtgoz.contract.model.ErrorNetworkException
import com.iamkurtgoz.contract.model.RestResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

internal inline fun <reified T> Flow<RestResult<T>>.buildDefaultFlow(
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): Flow<RestResult<T>> = onStart {
    emit(RestResult.Loading)
}.catch { e ->
    emit(
        RestResult.Error(
            ErrorNetworkException(
                errorMessage = e.message.orEmpty(),
                errorCode = 0
            )
        )
    )
}.flowOn(dispatcher)

suspend inline fun <reified T, R> combineResults(
    vararg results: RestResult<T>,
    crossinline transform: suspend (results: List<T>) -> R
): RestResult<R> {
    return if (results.all { it is RestResult.Success }) {
        RestResult.Success(
            transform(
                results.map {
                    (it as RestResult.Success).result
                }
            )
        )
    } else {
        results.filterIsInstance<RestResult.Error>().firstOrNull()!!
    }
}
