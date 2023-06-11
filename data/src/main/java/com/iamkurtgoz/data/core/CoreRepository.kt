package com.iamkurtgoz.data.core

import com.iamkurtgoz.contract.model.ErrorNetworkException
import com.iamkurtgoz.contract.model.RestResult
import com.iamkurtgoz.network.model.DataResponseWrapper
import retrofit2.Response

internal open class CoreRepository {

    inline val <reified T> Response<DataResponseWrapper<T>>.asRestResult: RestResult<T>
        get() {
            if (isSuccessful.not()) {
                return RestResult.Error(
                    error = ErrorNetworkException(
                        errorMessage = message(),
                        errorCode = code()
                    )
                )
            }

            val body = body() ?: return RestResult.Error(
                error = ErrorNetworkException(
                    errorMessage = body()?.message.orEmpty(),
                    errorCode = code()
                )
            )

            return body.data?.let {
                RestResult.Success(it)
            } ?: run {
                if (body.status) {
                    RestResult.Success(Unit as T)
                } else {
                    RestResult.Error(
                        error = ErrorNetworkException(
                            errorMessage = body.message.orEmpty(),
                            errorCode = code()
                        )
                    )
                }
            }
        }

    inline val <reified T> Response<T>.asRestResultAny: RestResult<T>
        get() {
            if (isSuccessful.not()) {
                return RestResult.Error(
                    error = ErrorNetworkException(
                        errorMessage = message(),
                        errorCode = code()
                    )
                )
            }

            val body = body() ?: return RestResult.Error(
                error = ErrorNetworkException(
                    errorMessage = message(),
                    errorCode = code()
                )
            )

            return RestResult.Success(body)
        }

    suspend inline fun <reified T : Any> request(
        crossinline call: suspend () -> Response<DataResponseWrapper<T>>
    ): RestResult<T> {
        return call.invoke().asRestResult
    }

    suspend inline fun <reified T : Any> requestAny(
        crossinline call: suspend () -> Response<T>
    ): RestResult<T> {
        return call.invoke().asRestResultAny
    }
}
