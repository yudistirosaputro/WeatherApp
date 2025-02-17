package com.yudistiro.domain.usecase

import com.yudistiro.domain.model.LocationModel
import com.yudistiro.domain.repository.GeocodeRepository
import javax.inject.Inject

class DeleteSavedLocationUseCase @Inject constructor(
    private val geocodeRepository: GeocodeRepository
) {
    suspend operator fun invoke(locationModel: LocationModel) {
        geocodeRepository.deleteSavedLocation(locationModel)
    }
}