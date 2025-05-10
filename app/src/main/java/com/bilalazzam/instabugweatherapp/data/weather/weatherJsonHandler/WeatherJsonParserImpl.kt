package com.bilalazzam.instabugweatherapp.data.weather.weatherJsonHandler

import com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.mapper.toDailyForecastList
import com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.mapper.toWeatherData
import com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.model.CurrentWeatherResponse
import com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.model.ForecastDay
import com.bilalazzam.instabugweatherapp.data.weather.datasource.remote.model.ForecastResponse
import com.bilalazzam.instabugweatherapp.domain.weather.model.DailyForecast
import com.bilalazzam.instabugweatherapp.domain.weather.model.WeatherData
import org.json.JSONObject

class WeatherJsonParserImpl : WeatherJsonParser {
    override fun parseCurrentWeather(json: String): WeatherData {
        val jsonObject = JSONObject(json).getJSONObject("currentConditions")
        return CurrentWeatherResponse(
            tempC = jsonObject.getDouble("temp"),
            feelsLikeC = jsonObject.getDouble("feelslike"),
            humidity = jsonObject.getInt("humidity"),
            conditions = jsonObject.getString("conditions")
        ).toWeatherData()
    }

    override fun parseForecastWeather(json: String): List<DailyForecast> {
        val daysArray = JSONObject(json).getJSONArray("days")
        val forecastList = mutableListOf<ForecastDay>()
        for (i in 0 until 5.coerceAtMost(daysArray.length())) {
            val day = daysArray.getJSONObject(i)
            forecastList.add(
                ForecastDay(
                    date = day.getString("datetime"),
                    tempMin = day.getDouble("tempmin"),
                    tempMax = day.getDouble("tempmax"),
                    conditions = day.getString("conditions")
                )
            )
        }
        return ForecastResponse(forecastList).toDailyForecastList()
    }
}