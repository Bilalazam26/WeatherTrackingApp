package com.bilalazzam.instabugweatherapp.data.weather.datasource.remote

import com.bilalazzam.instabugweatherapp.data.weather.datasource.WeatherDataSource
import com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.weatherApiService.WeatherApiService
import com.bilalazzam.instabugweatherapp.data.weather.weatherJsonHandler.WeatherJsonParser
import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate


class RemoteWeatherDataSource(
    private val weatherApiService: WeatherApiService,
    private val weatherJsonParser: WeatherJsonParser,
) : WeatherDataSource() {
    override fun getCurrentWeatherData(
        locationCoordinate: LocationCoordinate,
        callback: (Result<String>) -> Unit
    ) {
        runInBackground {
            try {
                val response = weatherApiService.getCurrentWeather(
                    locationCoordinate
                )
                weatherJsonParser.parseCurrentWeather(response)

                postResultToMainThread(Result.success(response), callback)
            } catch (exception: Exception) {
                postResultToMainThread(Result.failure(exception), callback)
            }

        }
    }

    override fun getForecastWeather(
        locationCoordinate: LocationCoordinate,
        callback: (Result<String>) -> Unit
    ) {
        runInBackground {
            try {
                val response = weatherApiService.getForecastWeather(
                    locationCoordinate
                )
                weatherJsonParser.parseForecastWeather(response)

                postResultToMainThread(Result.success(response), callback)
            } catch (exception: Exception) {
                postResultToMainThread(Result.failure(exception), callback)
            }
        }
    }
}