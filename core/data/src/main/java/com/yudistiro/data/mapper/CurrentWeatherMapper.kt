package com.yudistiro.data.mapper

import com.yudistiro.common.model.WeatherCondition
import com.yudistiro.common.util.DateTimeUtils
import com.yudistiro.common.util.EMPTY_STRING
import com.yudistiro.common.util.ZERO
import com.yudistiro.common.util.ZERO_DOUBLE
import com.yudistiro.data.helper.DomainMapper
import com.yudistiro.domain.model.CurrentWeather
import com.yudistiro.network.model.CurrentWeatherResponse

class CurrentWeatherMapper : DomainMapper<CurrentWeatherResponse, CurrentWeather> {
    override fun mapToDomainModel(dataModel: CurrentWeatherResponse): CurrentWeather {
        return with(dataModel) {
            val currentDate = DateTimeUtils().getCurrentDateInfo()
            CurrentWeather(
                temperature = mainInfo?.temp  ?: ZERO_DOUBLE,
                condition = weather.firstOrNull()?.id?.let {
                    WeatherCondition.fromWeatherId(it)
                } ?: WeatherCondition.SUNNY  ,
                humidity = mainInfo?.humidity ?: ZERO,
                windSpeed = wind?.speed ?: ZERO_DOUBLE,
                rainChance = weather.firstOrNull()?.description ?: EMPTY_STRING,
                day = " ${currentDate.day} ${currentDate.monthShort} ${currentDate.time}",
                locationName = name ?: EMPTY_STRING,
                time =  currentDate.timeWithAmPm
            )
        }
    }
}