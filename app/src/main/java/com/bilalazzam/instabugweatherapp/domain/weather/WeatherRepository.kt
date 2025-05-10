package com.bilalazzam.instabugweatherapp.domain.weather

import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate
import com.bilalazzam.instabugweatherapp.domain.weather.model.DailyForecast
import com.bilalazzam.instabugweatherapp.domain.weather.model.WeatherData

interface WeatherRepository {
    fun getCurrentWeatherData(
        locationCoordinate: LocationCoordinate,
        callback: (Result<WeatherData>) -> Unit
    )

    fun getForecastWeather(
        locationCoordinate: LocationCoordinate,
        callback: (Result<List<DailyForecast>>) -> Unit
    )
}