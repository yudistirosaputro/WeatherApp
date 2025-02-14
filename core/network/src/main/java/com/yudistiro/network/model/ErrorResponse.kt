package com.yudistiro.network.model

data class ErrorResponse(
    @SerialName("status_message")
    var statusMessage: String?,
    @SerialName("status_code")
    var statusCode: Int? = null,
)

