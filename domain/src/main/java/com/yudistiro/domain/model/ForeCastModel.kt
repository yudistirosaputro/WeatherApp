package com.yudistiro.domain.model

import com.yudistiro.common.model.WeatherCondition
import com.yudistiro.common.util.EMPTY_STRING
import com.yudistiro.common.util.ZERO_DOUBLE

data  class ForeCastModel (
    val temperature: Double = ZERO_DOUBLE,
    val condition: WeatherCondition = WeatherCondition.SUNNY,
    val time: String = EMPTY_STRING,
)