package com.yudistiro.data.repository

import com.yudistiro.common.util.UNITS
import com.yudistiro.data.helper.dataSourceHandling
import com.yudistiro.data.mapper.CurrentWeatherMapper
import com.yudistiro.data.mapper.ForecastWeatherMapper
import com.yudistiro.domain.model.CurrentWeatherModel
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.ForeCastModel
import com.yudistiro.domain.repository.WeatherRepository
import com.yudistiro.network.api.WeatherApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
) : WeatherRepository {
    override fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
    ): Flow<DomainResource<CurrentWeatherModel>> {
        return dataSourceHandling(
            callApi = {
                weatherApi.getCurrentWeather(
                    latitude,
                    longitude,
                    UNITS,
                )
            },
            mapper = CurrentWeatherMapper()
        )
    }

    override fun getForecast(id: Int): Flow<DomainResource<List<ForeCastModel>>> {
        return dataSourceHandling(
            callApi = {
                weatherApi.getForecast(id,
                    UNITS)
            },
            mapper = ForecastWeatherMapper()
        )
    }
}