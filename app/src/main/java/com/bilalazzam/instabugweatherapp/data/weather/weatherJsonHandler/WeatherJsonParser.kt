package com.bilalazzam.instabugweatherapp.data.weather.weatherJsonHandler

import com.bilalazzam.instabugweatherapp.domain.weather.model.DailyForecast
import com.bilalazzam.instabugweatherapp.domain.weather.model.WeatherData

interface WeatherJsonParser {
    fun parseCurrentWeather(json: String): WeatherData
    fun parseForecastWeather(json: String): List<DailyForecast>
}