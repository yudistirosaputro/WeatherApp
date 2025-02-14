package com.yudistiro.data.helper

import android.util.Log
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.network.model.ErrorResponse
import com.yudistiro.network.model.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.HttpURLConnection


internal fun <F : Any, T : Any> NetworkResponse<F, ErrorResponse>.mapToDomainResource(
    transformSuccess: DomainMapper<F, T>?,
): DomainResource<T> {
    return when (this) {
        is NetworkResponse.ErrorApi -> convertToDomainError()
        is NetworkResponse.ErrorNetwork -> {
            Log.e("ERRRO NETWORK","ERROR : ${error.message}")
            DomainResource.Error(
                error, "Koneksi Internet Tidak Tersedia"
            )

        }
        is NetworkResponse.ErrorUnknown -> {
            Log.e("ERRRO NETWORK","ERROR : ${error?.message}")
            DomainResource.Error(
                error = null,
                message = error?.message.orEmpty()
            )
        }

        is NetworkResponse.Success -> {
            if (transformSuccess == null) DomainResource.SuccessNoData("")
            else DomainResource.Success(
                data = transformSuccess.mapToDomainModel(data),
            )
        }

        is NetworkResponse.SuccessNoData -> DomainResource.SuccessNoData(message)
    }
}


fun <T : Any> NetworkResponse.ErrorApi<T, ErrorResponse>.convertToDomainError(): DomainResource.Error =
    when (code) {
        HttpURLConnection.HTTP_INTERNAL_ERROR,
        HttpURLConnection.HTTP_BAD_GATEWAY,
        HttpURLConnection.HTTP_GATEWAY_TIMEOUT,
        HttpURLConnection.HTTP_UNAVAILABLE
            -> DomainResource.Error(
            error = null,
            message = error.statusMessage.orEmpty()
        )

        else -> {
            Log.e("ERRRO NETWORK","ERROR : ${error.statusMessage}")
            DomainResource.Error(
                error = null,
                message = "Error Tidak di ketahui"
            )
        }
    }


fun <D : Any, T : Any> dataSourceHandling(
    callApi: suspend () -> NetworkResponse<D, ErrorResponse>,
    mapper: DomainMapper<D, T>? = null,
): Flow<DomainResource<T>> = flow {
    emit(DomainResource.Loading)
    val result: NetworkResponse<D, ErrorResponse> = callApi.invoke()
    emit(result.mapToDomainResource(mapper))
}




