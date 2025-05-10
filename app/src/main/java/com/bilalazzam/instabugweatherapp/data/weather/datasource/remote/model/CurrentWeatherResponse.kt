package com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.model

data class CurrentWeatherResponse(
    val tempC: Double,
    val feelsLikeC: Double,
    val humidity: Int,
    val conditions: String
)
