package com.yudistiro.domain.usecase

import com.yudistiro.domain.model.CurrentWeather
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weather: WeatherRepository
) {
    operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Flow<DomainResource<CurrentWeather>> {
        return weather.getCurrentWeather(
            latitude,
            longitude
        )
    }

}