package com.bilalazzam.instabugweatherapp.di

import android.content.Context
import com.bilalazzam.instabugweatherapp.BuildConfig
import com.bilalazzam.instabugweatherapp.data.location.LocationProviderImpl
import com.bilalazzam.instabugweatherapp.data.weather.WeatherRepositoryImpl
import com.bilalazzam.instabugweatherapp.data.weather.datasource.local.CacheDataSource
import com.bilalazzam.instabugweatherapp.data.weather.datasource.local.LocalWeatherDataSource
import com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.RemoteWeatherDataSource
import com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.weatherApiService.WeatherApiServiceImpl
import com.bilalazzam.instabugweatherapp.data.weather.weatherJsonHandler.JsonFileHandler
import com.bilalazzam.instabugweatherapp.data.weather.weatherJsonHandler.WeatherJsonParserImpl
import com.bilalazzam.instabugweatherapp.domain.location.LocationProvider
import com.bilalazzam.instabugweatherapp.domain.weather.WeatherRepository
import java.io.File

object DataModule {
    //location
    val locationProvider: LocationProvider = LocationProviderImpl()

    //weather
    val weatherApiService = WeatherApiServiceImpl(BuildConfig.API_KEY)
    lateinit var weatherJsonFileHandler: JsonFileHandler

    //daily forecast
    lateinit var dailyForecastJsonFileHandler: JsonFileHandler

    //datasource
    lateinit var cacheDataSource: CacheDataSource

    //repository
    lateinit var weatherRepository: WeatherRepository

    fun initialize(context: Context) {
        weatherJsonFileHandler = JsonFileHandler(File(context.cacheDir, "weather.json"))
        dailyForecastJsonFileHandler = JsonFileHandler(File(context.cacheDir, "dailyForecast.json"))

        cacheDataSource = LocalWeatherDataSource(
            weatherDataJsonFileHandler = weatherJsonFileHandler,
            dailyForecastJsonFileHandler = dailyForecastJsonFileHandler
        )

        val weatherJsonParser = WeatherJsonParserImpl()

        val remoteWeatherDataSource = RemoteWeatherDataSource(weatherApiService, weatherJsonParser)

        weatherRepository =
            WeatherRepositoryImpl(remoteWeatherDataSource, cacheDataSource, weatherJsonParser)

    }
}