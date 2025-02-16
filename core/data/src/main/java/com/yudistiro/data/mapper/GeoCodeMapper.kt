package com.yudistiro.data.mapper

import com.yudistiro.common.util.EMPTY_STRING
import com.yudistiro.common.util.ZERO_DOUBLE
import com.yudistiro.data.helper.DomainMapper
import com.yudistiro.domain.model.LocationModel
import com.yudistiro.network.model.GeolocationResponse

class GeolocationMapper : DomainMapper<List<GeolocationResponse>, List<LocationModel>> {
    override fun mapToDomainModel(dataModel: List<GeolocationResponse>): List<LocationModel> {
        return dataModel.map { response ->
            LocationModel(
                cityName = response.name ?: EMPTY_STRING,
                country = response.country ?: EMPTY_STRING,
                state = response.state ?: EMPTY_STRING,
                latitude = response.lat ?: ZERO_DOUBLE,
                longitude = response.lon ?: ZERO_DOUBLE,
            )
        }
    }
}
