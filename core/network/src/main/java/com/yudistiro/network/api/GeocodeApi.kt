package com.yudistiro.network.api

import com.yudistiro.network.model.ErrorResponse
import com.yudistiro.network.model.GeolocationResponse
import com.yudistiro.network.model.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodeApi {
    @GET("direct")
    suspend fun searchLocations(
        @Query("q") query: String,
        @Query("limit") limit: Int = 15,
    ): NetworkResponse<List<GeolocationResponse>, ErrorResponse>
}