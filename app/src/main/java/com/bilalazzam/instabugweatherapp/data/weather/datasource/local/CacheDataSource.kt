package com.bilalazzam.instabugweatherapp.data.weather.datasource.local

import com.bilalazzam.instabugweatherapp.data.weather.datasource.WeatherDataSource

abstract class CacheDataSource : WeatherDataSource() {
    abstract fun cacheWeatherData(weatherDataJson: String)
    abstract fun cacheForecastWeather(dailyForecastJson: String)
}