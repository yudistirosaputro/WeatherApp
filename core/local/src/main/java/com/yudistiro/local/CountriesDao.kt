package com.yudistiro.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yudistiro.local.entity.CountriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountryData(countriesEntity: CountriesEntity)

    @Query("SELECT * FROM countries ORDER BY id DESC")
    fun getAllCountriesData(): Flow<List<CountriesEntity>>

    @Query("DELETE FROM countries WHERE cityName = :cityName")
    suspend fun deleteOldWeatherData(cityName: String)

    @Query("SELECT * FROM countries ORDER BY id DESC LIMIT :limit")
    fun getLatestCountries(limit: Int): Flow<List<CountriesEntity>>
}