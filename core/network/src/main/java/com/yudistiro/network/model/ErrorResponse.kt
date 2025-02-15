package com.yudistiro.network.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status_message", alternate =[ "message"])
    var statusMessage: String?,
    @SerializedName("status_code", alternate =[ "cod"])
    var statusCode: Int? = null,
)

