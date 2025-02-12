package com.yudistiro.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountriesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cityName: String,
)