package com.yudistiro.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudistiro.domain.model.CurrentWeatherModel
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.ForeCastModel
import com.yudistiro.domain.usecase.GetCurrentWeatherUseCase
import com.yudistiro.domain.usecase.GetForecastByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val weatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastByIdUseCase: GetForecastByIdUseCase
) : ViewModel() {
    private val _weatherState =
        MutableLiveData<DomainResource<CurrentWeatherModel>>(DomainResource.Loading)
    val weatherState: LiveData<DomainResource<CurrentWeatherModel>> get() = _weatherState

    private val _forecastState =
        MutableLiveData<DomainResource<List<ForeCastModel>>>(DomainResource.Loading)
    val forecastState: LiveData<DomainResource<List<ForeCastModel>>> get() = _forecastState

    fun fetchWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            weatherUseCase(latitude, longitude)
                .collect { resource ->
                    _weatherState.value = resource
                }
        }
    }

    fun getForecastById(id: Int) {
        viewModelScope.launch {
            getForecastByIdUseCase(id)
                .collect { resource ->
                    _forecastState.value = resource
                }
        }
    }
}