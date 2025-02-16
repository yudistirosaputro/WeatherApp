package com.yudistiro.data.mapper

import com.yudistiro.common.model.WeatherCondition
import com.yudistiro.common.util.DateTimeUtils
import com.yudistiro.common.util.EMPTY_STRING
import com.yudistiro.common.util.ZERO
import com.yudistiro.common.util.ZERO_DOUBLE
import com.yudistiro.data.helper.DomainMapper
import com.yudistiro.domain.model.CurrentWeatherModel
import com.yudistiro.network.model.CurrentWeatherResponse

class CurrentWeatherMapper : DomainMapper<CurrentWeatherResponse, CurrentWeatherModel> {
    override fun mapToDomainModel(dataModel: CurrentWeatherResponse): CurrentWeatherModel {
        return with(dataModel) {
            val currentDate = DateTimeUtils().getCurrentDateInfo()
            CurrentWeatherModel(
                id = id ?: ZERO,
                temperature = mainInfo?.temp  ?: ZERO_DOUBLE,
                condition = weather.firstOrNull()?.id?.let {
                    WeatherCondition.fromWeatherId(it)
                } ?: WeatherCondition.SUNNY  ,
                humidity = mainInfo?.humidity ?: ZERO,
                windSpeed = wind?.speed ?: ZERO_DOUBLE,
                rainChance = weather.firstOrNull()?.description ?: EMPTY_STRING,
                day = currentDate.day,
                locationName = name ?: EMPTY_STRING,
                time =  currentDate.timeWithAmPm
            )
        }
    }
}