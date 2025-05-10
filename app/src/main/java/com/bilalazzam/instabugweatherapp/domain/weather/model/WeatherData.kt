package com.bilalazzam.instabugweatherapp.domain.weather.model

data class WeatherData(
    val temperatureC: Double,
    val condition: String,
    val feelsLikeC: Double,
    val humidity: Int
)