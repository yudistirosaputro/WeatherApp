package com.yudistiro.uikit.model

import java.util.Date

data class HourlyForecast(
    val time: Date,
    val temperature: Int,
    val weatherCondition: WeatherCondition
)

enum class WeatherCondition {
    SUNNY,
    CLOUDY,
    RAINY,
    PARTLY_CLOUDY
}