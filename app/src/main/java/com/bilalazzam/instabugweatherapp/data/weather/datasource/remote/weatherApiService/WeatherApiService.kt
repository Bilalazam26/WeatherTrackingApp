package com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.weatherApiService


import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate

interface WeatherApiService {
    fun getCurrentWeather(locationCoordinate: LocationCoordinate): String
    fun getForecastWeather(locationCoordinate: LocationCoordinate): String
}