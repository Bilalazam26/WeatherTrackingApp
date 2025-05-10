package com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.mapper

import com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.model.CurrentWeatherResponse
import com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.model.ForecastResponse
import com.bilalazzam.instabugweatherapp.domain.weather.model.DailyForecast
import com.bilalazzam.instabugweatherapp.domain.weather.model.WeatherData

fun CurrentWeatherResponse.toWeatherData() = WeatherData(
    temperatureC = tempC,
    condition = conditions,
    feelsLikeC = feelsLikeC,
    humidity = humidity
)

fun ForecastResponse.toDailyForecastList() = days.map {
    DailyForecast(
        date = it.date,
        minTemperatureC = it.tempMin,
        maxTemperatureC = it.tempMax,
        condition = it.conditions
    )
}