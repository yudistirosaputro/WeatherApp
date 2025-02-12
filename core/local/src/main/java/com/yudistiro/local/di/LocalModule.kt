package com.yudistiro.local.di

import android.content.Context
import androidx.room.Room
import com.yudistiro.local.CountriesDao
import com.yudistiro.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {
    @Singleton
    @Provides
    fun provideWeatherDatabase(context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            WeatherDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration() // Use with caution in production
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherDao(database: WeatherDatabase): CountriesDao {
        return database.weatherDao()
    }
}