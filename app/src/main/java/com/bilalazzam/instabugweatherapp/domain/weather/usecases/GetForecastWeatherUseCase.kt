package com.bilalazzam.instabugweatherapp.domain.weather.usecases

import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate
import com.bilalazzam.instabugweatherapp.domain.weather.WeatherRepository
import com.bilalazzam.instabugweatherapp.domain.weather.model.DailyForecast

class GetForecastWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    fun execute(
        locationCoordinate: LocationCoordinate,
        callback: (Result<List<DailyForecast>>) -> Unit
    ) {
        weatherRepository.getForecastWeather(locationCoordinate) { result ->
            callback(result)
        }
    }

}