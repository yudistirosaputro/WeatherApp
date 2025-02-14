package com.yudistiro.network.api

import com.yudistiro.network.model.CurrentWeatherResponse
import com.yudistiro.network.model.ErrorResponse
import com.yudistiro.network.model.ForecastWeatherResponse
import com.yudistiro.network.model.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
    ): NetworkResponse<CurrentWeatherResponse,ErrorResponse>

    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("id") apiKey: String
    ): NetworkResponse<ForecastWeatherResponse,ErrorResponse>
}