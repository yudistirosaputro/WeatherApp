package com.yudistiro.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yudistiro.common.util.EMPTY_STRING
import com.yudistiro.common.util.ZERO

@Entity(tableName = "countries")
data class CountriesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = ZERO,
    val cityName: String = EMPTY_STRING,
    val country: String = EMPTY_STRING,
    val state: String = EMPTY_STRING,
    val lat: Double,
    val lng: Double,
)