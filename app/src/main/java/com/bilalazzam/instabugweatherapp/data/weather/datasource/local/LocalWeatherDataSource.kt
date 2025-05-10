package com.bilalazzam.instabugweatherapp.data.weather.datasource.local

import android.util.Log
import com.bilalazzam.instabugweatherapp.data.weather.weatherJsonHandler.JsonFileHandler
import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate

class LocalWeatherDataSource(
    private val weatherDataJsonFileHandler: JsonFileHandler,
    private val dailyForecastJsonFileHandler: JsonFileHandler
) : CacheDataSource() {
    override fun getCurrentWeatherData(
        locationCoordinate: LocationCoordinate,
        callback: (Result<String>) -> Unit
    ) {
        runInBackground {
            try {
                val json = weatherDataJsonFileHandler.readJson()
                Log.d("TAG", "getCurrentWeatherData: get From Cache $json")
                postResultToMainThread(Result.success(json), callback)
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
                val json = dailyForecastJsonFileHandler.readJson()
                postResultToMainThread(Result.success(json), callback)
            } catch (exception: Exception) {
                postResultToMainThread(Result.failure(exception), callback)
            }
        }
    }

    override fun cacheWeatherData(weatherDataJson: String) {
        Log.d("TAG", "cacheWeatherData: $weatherDataJson")
        weatherDataJsonFileHandler.writeJson(weatherDataJson)
    }

    override fun cacheForecastWeather(dailyForecastJson: String) {
        dailyForecastJsonFileHandler.writeJson(dailyForecastJson)
    }
}