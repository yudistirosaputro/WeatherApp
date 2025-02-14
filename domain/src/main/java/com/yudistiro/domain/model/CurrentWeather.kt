package com.yudistiro.domain.model

data class CurrentWeather (
    val temperature: Double,
    val condition: String,
    val icon: String,
    val windSpeed: Double,
    val humidity: Int,
    val uvIndex: Double,
    val rainChance: String,
    val time: String
)