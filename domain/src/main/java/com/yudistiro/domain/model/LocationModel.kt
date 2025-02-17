package com.yudistiro.domain.model

import android.os.Parcelable
import com.yudistiro.common.util.EMPTY_STRING
import com.yudistiro.common.util.ZERO_DOUBLE
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationModel(
    val cityName: String = EMPTY_STRING,
    val country: String = EMPTY_STRING,
    val state: String = EMPTY_STRING,
    val latitude: Double = ZERO_DOUBLE,
    val longitude: Double = ZERO_DOUBLE,
    val localName: String = EMPTY_STRING,
    var isFavorite: Boolean = false
) : Parcelable
