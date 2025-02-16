package com.yudistiro.domain.usecase

import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.LocationModel
import com.yudistiro.domain.repository.GeocodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchLocationWeatherUseCase @Inject constructor(
    private val geocodeRepository: GeocodeRepository
) {
    operator fun invoke(query : String) :Flow<DomainResource<List<LocationModel>>> {
        return geocodeRepository.searchLocations(query)
    }
}