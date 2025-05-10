package com.bilalazzam.instabugweatherapp.domain.weather.usecases

import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate
import com.bilalazzam.instabugweatherapp.domain.weather.WeatherRepository
import com.bilalazzam.instabugweatherapp.domain.weather.model.WeatherData

class GetCurrentWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    fun execute(locationCoordinate: LocationCoordinate, callback: (Result<WeatherData>) -> Unit) {
        weatherRepository.getCurrentWeatherData(locationCoordinate) { result ->
            callback(result)
        }
    }
}