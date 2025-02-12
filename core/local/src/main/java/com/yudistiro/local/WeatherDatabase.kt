package com.yudistiro.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yudistiro.local.entity.CountriesEntity

@Database(
    entities = [CountriesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): CountriesDao

    companion object {
        const val DATABASE_NAME = "weather_database"
    }
}