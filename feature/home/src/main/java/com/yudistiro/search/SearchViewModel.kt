package com.yudistiro.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.LocationModel
import com.yudistiro.domain.usecase.SearchLocationWeatherUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchLocationWeatherUseCase: SearchLocationWeatherUseCase
) : ViewModel() {
    private val _searchResults = MutableLiveData<DomainResource<List<LocationModel>>>(DomainResource.Loading)
    val searchResults: LiveData<DomainResource<List<LocationModel>>> get() = _searchResults

    fun searchLocations(query: String) {
        viewModelScope.launch {
            searchLocationWeatherUseCase(query).collect { resource ->
                _searchResults.value = resource
            }
        }
    }
}