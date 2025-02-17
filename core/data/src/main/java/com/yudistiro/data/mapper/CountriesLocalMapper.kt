package com.yudistiro.data.mapper

import com.yudistiro.domain.model.LocationModel
import com.yudistiro.local.entity.CountriesEntity

class CountriesLocalMapper {
    fun mapToDomainModel(dataModel:CountriesEntity): LocationModel {
        return with(dataModel) {
            LocationModel(
                cityName = cityName,
                country = country,
                state = state,
                latitude = lat,
                longitude = lng,
            )
        }
    }
    fun mapToLocalModel(dataModel : LocationModel): CountriesEntity {
        return with(dataModel) {
            CountriesEntity(
                cityName = cityName,
                country = country,
                state = state,
                lat = latitude,
                lng = longitude,
            )
        }
    }
}