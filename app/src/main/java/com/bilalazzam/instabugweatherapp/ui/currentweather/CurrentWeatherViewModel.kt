package com.bilalazzam.instabugweatherapp.ui.currentweather

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bilalazzam.instabugweatherapp.domain.location.GetLocationUseCase
import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate
import com.bilalazzam.instabugweatherapp.domain.weather.usecases.GetCurrentWeatherUseCase
import com.bilalazzam.instabugweatherapp.ui.utils.hasLocationPermission

class CurrentWeatherViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getLocationUseCase: GetLocationUseCase
) {
    var uiState by mutableStateOf(CurrentWeatherUiState())
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
                getCurrentWeather(locationCoordinate)
            },
            onError = {
                uiState = uiState.copy(
                    isLoading = false,
                    error = it
                )
            }
        )
    }

    fun getCurrentWeather(locationCoordinate: LocationCoordinate) {
        uiState = uiState.copy(isLoading = true)
        getCurrentWeatherUseCase.execute(locationCoordinate) { result ->
            result
                .onSuccess { weatherData ->
                    uiState = uiState.copy(
                        weatherData = weatherData,
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