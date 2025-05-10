package com.bilalazzam.instabugweatherapp.data.weather.datasource

import android.os.Handler
import android.os.Looper
import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate

abstract class WeatherDataSource {
    abstract fun getCurrentWeatherData(
        locationCoordinate: LocationCoordinate,
        callback: (Result<String>) -> Unit
    )

    abstract fun getForecastWeather(
        locationCoordinate: LocationCoordinate,
        callback: (Result<String>) -> Unit
    )

    protected fun runInBackground(task: () -> Unit) {
        Thread(task).start()
    }

    protected fun postResultToMainThread(
        result: Result<String>,
        callback: (Result<String>) -> Unit
    ) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            callback(result)
        }
    }
}