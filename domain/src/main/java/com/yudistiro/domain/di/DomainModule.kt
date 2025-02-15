package com.yudistiro.domain.di

import com.yudistiro.domain.repository.WeatherRepository
import com.yudistiro.domain.usecase.GetCurrentWeatherUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetCurrentWeatherUseCase(
        weatherRepository: WeatherRepository
    ) : GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(weatherRepository)
    }
}