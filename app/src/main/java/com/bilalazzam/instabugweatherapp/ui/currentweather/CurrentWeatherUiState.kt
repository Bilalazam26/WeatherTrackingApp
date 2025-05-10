package com.bilalazzam.instabugweatherapp.ui.currentweather

import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate
import com.bilalazzam.instabugweatherapp.domain.weather.model.WeatherData

data class CurrentWeatherUiState(
    val weatherData: WeatherData? = null,
    val location: LocationCoordinate? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)