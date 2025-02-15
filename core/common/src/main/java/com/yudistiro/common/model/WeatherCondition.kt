package com.yudistiro.common.model

enum class WeatherCondition {
    SUNNY,
    CLOUDS,
    RAIN,
    PARTLY_CLOUDY;

    companion object {
        fun fromWeatherId(id: Int): WeatherCondition = when (id) {
            in 200..232 -> RAIN  // Thunderstorm
            in 300..321 -> RAIN  // Drizzle
            in 500..531 -> RAIN  // Rain
            in 600..622 -> RAIN  // Snow
            in 701..781 -> CLOUDS // Atmosphere
            800 -> SUNNY  // Clear
            in 801..804 -> CLOUDS  // Clouds
            else -> SUNNY  // Default
        }
    }
}
