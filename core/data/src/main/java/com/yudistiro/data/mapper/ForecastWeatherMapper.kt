package com.yudistiro.data.mapper

import com.yudistiro.common.model.WeatherCondition
import com.yudistiro.common.util.DateTimeUtils
import com.yudistiro.common.util.EMPTY_STRING
import com.yudistiro.common.util.ZERO_DOUBLE
import com.yudistiro.data.helper.DomainMapper
import com.yudistiro.domain.model.ForeCastModel
import com.yudistiro.network.model.ForecastWeatherResponse
import java.time.LocalDate

class ForecastWeatherMapper : DomainMapper<ForecastWeatherResponse, List<ForeCastModel>> {
    override fun mapToDomainModel(dataModel: ForecastWeatherResponse): List<ForeCastModel> {
        return dataModel.weatherInfo.filter { forecastResponse ->
            DateTimeUtils().isValidForecastTime(forecastResponse.dtTxt.orEmpty())
        }  .map { forecastResponse ->
            ForeCastModel(
                temperature = forecastResponse.mainInfo?.temp ?: ZERO_DOUBLE,
                condition = forecastResponse.weather.firstOrNull()?.id?.let {
                    WeatherCondition.fromWeatherId(it)
                } ?: WeatherCondition.SUNNY,
                time = DateTimeUtils().convertToHoursOnly(forecastResponse.dtTxt ?: EMPTY_STRING)
            )
        }
    }
}