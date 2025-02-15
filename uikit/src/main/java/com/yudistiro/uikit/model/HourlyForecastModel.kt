package com.yudistiro.uikit.model

import com.yudistiro.common.model.WeatherCondition
import java.util.Date

data class HourlyForecast(
    val time: Date,
    val temperature: Int,
    val weatherCondition: WeatherCondition
)

