package com.yudistiro.data.di

import com.yudistiro.data.repository.WeatherRepositoryImpl
import com.yudistiro.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl) : WeatherRepository
}