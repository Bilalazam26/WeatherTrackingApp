package com.bilalazzam.instabugweatherapp.domain.weather.usecases

import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate
import com.bilalazzam.instabugweatherapp.domain.weather.WeatherRepository
import com.bilalazzam.instabugweatherapp.domain.weather.model.DailyForecast
import com.bilalazzam.instabugweatherapp.domain.weather.model.WeatherData
import java.io.IOException

class FakeWeatherRepository(
    private val shouldReturnError: Boolean = false,
    private val mockWeatherData: WeatherData? = null,
    private val mockForecastData: List<DailyForecast>? = null,
    private val mockException: Exception? = null
) : WeatherRepository {
    override fun getCurrentWeatherData(
        locationCoordinate: LocationCoordinate,
        callback: (Result<WeatherData>) -> Unit
    ) {
        if (shouldReturnError) {
            callback(Result.failure(mockException ?: IOException("Network error")))
        } else {
            callback(Result.success(mockWeatherData ?: WeatherData(25.0, "Sunny", 25.0, 50)))
        }
    }

    override fun getForecastWeather(
        locationCoordinate: LocationCoordinate,
        callback: (Result<List<DailyForecast>>) -> Unit
    ) {
        if (shouldReturnError) {
            callback(Result.failure(mockException ?: IOException("Network error")))
        } else {
            callback(Result.success(mockForecastData ?: listOf(DailyForecast("2023-05-10", 25.0, 31.0, "Sunny"))))
        }
    }
}