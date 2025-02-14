package com.yudistiro.domain.model

sealed class DomainResource<out Data> {
    data class Success<Data>(
        val data: Data,
        val message: String? = null
    ) : DomainResource<Data>()

    data class SuccessNoData(
        val message: String
    ) : DomainResource<Nothing>()

    data class Error(
        var error: Exception?,
        var message: String
    ) : DomainResource<Nothing>()


    data object Loading : DomainResource<Nothing>()
}
