package com.bilalazzam.instabugweatherapp.ui

sealed class AppScreens {
    object CurrentWeatherScreen : AppScreens()
    object ForecastScreen : AppScreens()
}