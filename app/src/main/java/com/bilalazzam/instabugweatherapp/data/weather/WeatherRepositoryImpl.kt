package com.bilalazzam.instabugweatherapp.data.weather

import android.util.Log
import com.bilalazzam.instabugweatherapp.data.weather.datasource.local.CacheDataSource
import com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.RemoteWeatherDataSource
import com.bilalazzam.instabugweatherapp.data.weather.weatherJsonHandler.WeatherJsonParser
import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate
import com.bilalazzam.instabugweatherapp.domain.weather.WeatherRepository
import com.bilalazzam.instabugweatherapp.domain.weather.model.DailyForecast
import com.bilalazzam.instabugweatherapp.domain.weather.model.WeatherData

class WeatherRepositoryImpl(
    private val remoteWeatherDataSource: RemoteWeatherDataSource,
    private val cacheDataSource: CacheDataSource,
    private val weatherJsonParser: WeatherJsonParser
) : WeatherRepository {
    override fun getCurrentWeatherData(
        locationCoordinate: LocationCoordinate,
        callback: (Result<WeatherData>) -> Unit
    ) {
        remoteWeatherDataSource.getCurrentWeatherData(
            locationCoordinate = locationCoordinate
        ) { result ->
            result.apply {
                onSuccess { weatherDataJson ->
                    try {
                        Log.d("TAG", "getCurrentWeatherData: coming from remote $weatherDataJson")
                        cacheDataSource.cacheWeatherData(weatherDataJson)

                        val weatherData = weatherJsonParser.parseCurrentWeather(weatherDataJson)
                        callback(Result.success(weatherData))
                    } catch (exception: Exception) {
                        callback(Result.failure(exception))
                    }
                }

                onFailure {
                    Log.d("TAG", "getCurrentWeatherData: failure from remote!!!!!!!!!$it")
                    cacheDataSource.getCurrentWeatherData(locationCoordinate) { cacheResult ->
                        cacheResult.apply {
                            onSuccess { json ->
                                try {
                                    val weatherData = weatherJsonParser.parseCurrentWeather(json)
                                    callback(Result.success(weatherData))
                                } catch (e: Exception) {
                                    callback(Result.failure(e))
                                }
                            }

                            onFailure { callback(Result.failure(it)) }
                        }
                    }
                }
            }
        }
    }

    override fun getForecastWeather(
        locationCoordinate: LocationCoordinate,
        callback: (Result<List<DailyForecast>>) -> Unit
    ) {
        remoteWeatherDataSource.getForecastWeather(
            locationCoordinate = locationCoordinate
        ) { result ->
            result.apply {
                onSuccess { dailyForecastJson ->
                    try {
                        cacheDataSource.cacheForecastWeather(dailyForecastJson)
                        val dailyForecast =
                            weatherJsonParser.parseForecastWeather(dailyForecastJson)
                        callback(Result.success(dailyForecast))
                    } catch (exception: Exception) {
                        callback(Result.failure(exception))

                    }
                }

                onFailure { cacheResult ->
                    cacheResult.apply {
                        onSuccess { json ->
                            try {
                                val dailyForecast = weatherJsonParser.parseForecastWeather(json)
                                callback(Result.success(dailyForecast))
                            } catch (e: Exception) {
                                callback(Result.failure(e))
                            }
                        }

                        onFailure { callback(Result.failure(it)) }
                    }
                }
            }
        }
    }

}