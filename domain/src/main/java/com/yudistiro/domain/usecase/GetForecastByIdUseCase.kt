package com.yudistiro.domain.usecase

import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.ForeCastModel
import com.yudistiro.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetForecastByIdUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(id : Int): Flow<DomainResource<List<ForeCastModel>>> {
        return weatherRepository.getForecast(id = id)
    }
}