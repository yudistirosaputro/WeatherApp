package com.yudistiro.domain.model

import com.yudistiro.common.util.EMPTY_STRING
import com.yudistiro.common.util.ZERO_DOUBLE

data class LocationModel(
    val cityName: String = EMPTY_STRING,
    val country: String = EMPTY_STRING,
    val state: String = EMPTY_STRING,
    val latitude: Double = ZERO_DOUBLE,
    val longitude: Double = ZERO_DOUBLE,
    val localName: String = EMPTY_STRING,
    val isFavorite: Boolean = false
)
