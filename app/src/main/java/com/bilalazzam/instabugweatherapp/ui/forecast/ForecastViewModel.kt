package com.bilalazzam.instabugweatherapp.ui.forecast

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bilalazzam.instabugweatherapp.domain.location.GetLocationUseCase
import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate
import com.bilalazzam.instabugweatherapp.domain.weather.usecases.GetForecastWeatherUseCase
import com.bilalazzam.instabugweatherapp.ui.utils.hasLocationPermission

class ForecastViewModel(
    private val getForecastWeatherUseCase: GetForecastWeatherUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {
    var uiState by mutableStateOf(ForecastUiState())
        private set

    fun getCurrentLocation(context: Context) {
        if (!hasLocationPermission(context)) {
            uiState = uiState.copy(
                isLoading = false,
                error = "Location Permission not granted"
            )
            return
        }
        getLocationUseCase.execute(
            context = context,
            onSuccess = { locationCoordinate ->
                uiState = uiState.copy(location = locationCoordinate)
                getForecastWeather(locationCoordinate)
            },
            onError = {
                uiState = uiState.copy(error = it)
            }
        )
    }

    fun getForecastWeather(locationCoordinate: LocationCoordinate) {
        getForecastWeatherUseCase.execute(locationCoordinate) { result ->
            result
                .onSuccess { forecastList ->
                    uiState = uiState.copy(
                        forecastList = forecastList,
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure { exception ->
                    uiState = uiState.copy(
                        isLoading = false,
                        error = exception.message
                    )
                }
        }
    }
}