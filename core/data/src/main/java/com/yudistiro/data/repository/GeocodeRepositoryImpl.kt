package com.yudistiro.data.repository

import com.yudistiro.data.helper.dataSourceHandling
import com.yudistiro.data.mapper.CountriesLocalMapper
import com.yudistiro.data.mapper.GeolocationMapper
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.LocationModel
import com.yudistiro.domain.repository.GeocodeRepository
import com.yudistiro.local.CountriesDao
import com.yudistiro.network.api.GeocodeApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GeocodeRepositoryImpl @Inject constructor(
    private val geocodeApi: GeocodeApi,
    private val countriesDao: CountriesDao
) : GeocodeRepository {
    override fun searchLocations(query: String): Flow<DomainResource<List<LocationModel>>> {
        return dataSourceHandling(
            callApi = {
                geocodeApi.searchLocations(query)
            },
            mapper = GeolocationMapper()
        )
    }

    override suspend fun getSavedLocations(limit: Int): Flow<DomainResource<List<LocationModel>>>  = flow{
      try {
          val savedLocation = if(limit == 0) countriesDao.getAllCountriesData() else
              countriesDao.getLatestCountries(limit)
          val locationModel =  savedLocation.first().map {
              LocationModel(
                  cityName = it.cityName,
                  country = it.country,
                  state = it.state,
                  latitude = it.lat,
                  longitude = it.lng,
                  isFavorite = true
              )
          }
          if(locationModel.isEmpty()) {
              return@flow emit(DomainResource.SuccessNoData("NO daa"))
          } else return@flow emit(DomainResource.Success(locationModel))
      } catch (e : Exception) {
          return@flow emit(DomainResource.Error(
              error = e,
              message = "Error get database : ${e.message}"
          ))
      }

    }

    override suspend fun saveLocation(location: LocationModel) {
        countriesDao.insertCountryData(
            CountriesLocalMapper().mapToLocalModel(location)
        )
    }

    override suspend fun deleteSavedLocation(location: LocationModel) {
        countriesDao.deleteOldWeatherData(location.cityName)
    }
}