package com.yudistiro.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudistiro.domain.model.CurrentWeather
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.usecase.GetCurrentWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val weatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {
    private val _weatherState =
        MutableLiveData<DomainResource<CurrentWeather>>(DomainResource.Loading)
    val weatherState: LiveData<DomainResource<CurrentWeather>> get() = _weatherState

    fun fetchWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            weatherUseCase(latitude, longitude)
                .collect { resource ->
                    _weatherState.value = resource
                }
        }
    }
}