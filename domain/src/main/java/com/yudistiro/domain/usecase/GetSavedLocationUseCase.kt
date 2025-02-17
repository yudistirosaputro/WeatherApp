package com.yudistiro.domain.usecase

import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.LocationModel
import com.yudistiro.domain.repository.GeocodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedLocationUseCase @Inject constructor(
    private val geocodeRepository: GeocodeRepository
) {
    suspend operator fun invoke() :Flow<DomainResource<List<LocationModel>>> {
        return geocodeRepository.getSavedLocations()
    }
}