package com.yudistiro.data.di

import com.yudistiro.data.repository.GeocodeRepositoryImpl
import com.yudistiro.data.repository.WeatherRepositoryImpl
import com.yudistiro.domain.repository.GeocodeRepository
import com.yudistiro.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl) : WeatherRepository

    @Binds
    abstract fun bindGeocodeRepository(geocodeRepositoryImpl: GeocodeRepositoryImpl) : GeocodeRepository
}