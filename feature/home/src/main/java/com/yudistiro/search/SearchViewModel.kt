package com.yudistiro.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.LocationModel
import com.yudistiro.domain.usecase.DeleteSavedLocationUseCase
import com.yudistiro.domain.usecase.GetSavedLocationUseCase
import com.yudistiro.domain.usecase.SaveLocationDataUseCase
import com.yudistiro.domain.usecase.SearchLocationWeatherUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchLocationWeatherUseCase: SearchLocationWeatherUseCase,
    private val saveLocationDataUseCase: SaveLocationDataUseCase,
    private val getSavedLocationUseCase: GetSavedLocationUseCase,
    private val deleteSavedLocationUseCase: DeleteSavedLocationUseCase
) : ViewModel() {
    private val _searchResults = MutableLiveData<DomainResource<List<LocationModel>>>()
    val searchResults: LiveData<DomainResource<List<LocationModel>>> get() = _searchResults

    private val _savedLocations = MutableLiveData<DomainResource<List<LocationModel>>>()
    val savedLocations: LiveData<DomainResource<List<LocationModel>>> get() =  _savedLocations

    fun searchLocations(query: String) {
        viewModelScope.launch {
            searchLocationWeatherUseCase(query).collect { resource ->
                _searchResults.value = resource
            }
        }
    }

    fun saveLocation(location: LocationModel) {
        viewModelScope.launch {
            saveLocationDataUseCase(location)
        }
    }
    fun deleteLocation(location: LocationModel) {
        viewModelScope.launch {
            deleteSavedLocationUseCase(location)
        }
    }

    fun getSavedLocation() {
        viewModelScope.launch {
            getSavedLocationUseCase().collect { resource ->
                _savedLocations.value = resource
            }
        }
    }
}