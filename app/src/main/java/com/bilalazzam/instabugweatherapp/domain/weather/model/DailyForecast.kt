package com.bilalazzam.instabugweatherapp.domain.weather.model

data class DailyForecast(
    val date: String,
    val minTemperatureC: Double,
    val maxTemperatureC: Double,
    val condition: String
)