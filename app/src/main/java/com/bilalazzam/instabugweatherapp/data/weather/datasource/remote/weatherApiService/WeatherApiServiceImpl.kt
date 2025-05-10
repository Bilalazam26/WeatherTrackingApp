package com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.weatherApiService

import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate
import java.net.HttpURLConnection
import java.net.URL

class WeatherApiServiceImpl(
    private val apiKey: String
) : WeatherApiService {
    override fun getCurrentWeather(
        locationCoordinate: LocationCoordinate
    ): String {
        val url = buildUrl(
            locationCoordinate,
            dayPath = "today",
            includes = "current"
        )

        return handleRequest(url)
    }

    override fun getForecastWeather(
        locationCoordinate: LocationCoordinate
    ): String {
        val url = buildUrl(
            locationCoordinate,
            dayPath = "",
            includes = "days&elements=datetime,tempmin,tempmax,conditions"
        )
        return handleRequest(url)
    }

    private fun buildUrl(
        locationCoordinate: LocationCoordinate,
        dayPath: String,
        includes: String
    ): String {
        return "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" +
                "${locationCoordinate.latitude},${locationCoordinate.longitude}/$dayPath?key=$apiKey&unitGroup=metric&include=$includes"
    }

    private fun handleRequest(
        url: String
    ): String {
        return try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.inputStream.bufferedReader().use { it.readText() }
        } catch (exception: Exception) {
            throw Exception("Network Error : ${exception.message}")
        }
    }
}