package com.yudistiro.domain.model

import com.yudistiro.common.model.WeatherCondition
import com.yudistiro.common.util.EMPTY_STRING
import com.yudistiro.common.util.ZERO
import com.yudistiro.common.util.ZERO_DOUBLE

data class CurrentWeather (
    val temperature: Double = ZERO_DOUBLE,
    val condition: WeatherCondition = WeatherCondition.SUNNY,
    val windSpeed: Double = ZERO_DOUBLE,
    val humidity: Int = ZERO,
    val uvIndex: Double = ZERO_DOUBLE,
    val rainChance: String = EMPTY_STRING,
    val time: String = EMPTY_STRING,
    val day:String = EMPTY_STRING,
    val locationName:String = EMPTY_STRING
)