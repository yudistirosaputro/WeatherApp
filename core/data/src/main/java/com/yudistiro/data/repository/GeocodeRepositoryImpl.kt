package com.yudistiro.data.repository

import com.yudistiro.data.helper.dataSourceHandling
import com.yudistiro.data.mapper.GeolocationMapper
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.LocationModel
import com.yudistiro.domain.repository.GeocodeRepository
import com.yudistiro.network.api.GeocodeApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GeocodeRepositoryImpl @Inject constructor(
    private val geocodeApi: GeocodeApi
) : GeocodeRepository {
    override fun searchLocations(query: String): Flow<DomainResource<List<LocationModel>>> {
        return dataSourceHandling(
            callApi = {
                geocodeApi.searchLocations(query)
            },
            mapper = GeolocationMapper()
        )
    }
}