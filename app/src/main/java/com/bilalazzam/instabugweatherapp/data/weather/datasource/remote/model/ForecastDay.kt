package com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.model

data class ForecastDay(
    val date: String,
    val tempMin: Double,
    val tempMax: Double,
    val conditions: String
)