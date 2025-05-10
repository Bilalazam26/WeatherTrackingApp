package com.bilalazzam.instabugweatherapp.ui.forecast

import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate
import com.bilalazzam.instabugweatherapp.domain.weather.model.DailyForecast

data class ForecastUiState(
    val forecastList: List<DailyForecast> = emptyList(),
    val location: LocationCoordinate? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)