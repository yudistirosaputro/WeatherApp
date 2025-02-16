package com.yudistiro.domain.repository

import com.yudistiro.domain.model.CurrentWeatherModel
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.ForeCastModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(latitude: Double, longitude: Double): Flow<DomainResource<CurrentWeatherModel>>
    fun getForecast(id: Int) :Flow<DomainResource<List<ForeCastModel>>>

}