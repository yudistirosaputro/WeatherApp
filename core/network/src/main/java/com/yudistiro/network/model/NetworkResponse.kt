package com.yudistiro.network.model

import java.io.IOException

sealed class NetworkResponse<S, E> {
    data class Success<S : Any, E>(
        val data: S
    ) : NetworkResponse<S, E>()

    data class SuccessNoData<S : Any, E>(
        val message: String
    ) : NetworkResponse<S, E>()

    data class ErrorApi<S : Any, E>(
        var code: Int,
        val error: E
    ) : NetworkResponse<S, E>()

    data class ErrorNetwork<S : Any, E>(val error: IOException) : NetworkResponse<S, E>()
    data class ErrorUnknown<S : Any, E>(val error: Throwable?) : NetworkResponse<S, E>()
}
