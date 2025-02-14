package com.yudistiro.domain.repository

import com.yudistiro.domain.model.DomainResource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(latitude: Double, longitude: Double): Flow<DomainResource<CurrentWeather>>
}