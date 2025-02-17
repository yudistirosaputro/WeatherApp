package com.yudistiro.domain.repository

import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface GeocodeRepository {
fun searchLocations(query: String): Flow<DomainResource<List<LocationModel>>>
suspend fun getSavedLocations(limit: Int = 0): Flow<DomainResource<List<LocationModel>>>
suspend fun saveLocation(location: LocationModel)
suspend fun deleteSavedLocation(location: LocationModel)
}