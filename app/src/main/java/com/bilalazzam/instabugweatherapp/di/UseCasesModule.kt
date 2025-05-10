package com.bilalazzam.instabugweatherapp.di

import com.bilalazzam.instabugweatherapp.di.DataModule.locationProvider
import com.bilalazzam.instabugweatherapp.di.DataModule.weatherRepository
import com.bilalazzam.instabugweatherapp.domain.location.GetLocationUseCase
import com.bilalazzam.instabugweatherapp.domain.weather.usecases.GetCurrentWeatherUseCase
import com.bilalazzam.instabugweatherapp.domain.weather.usecases.GetForecastWeatherUseCase

object UseCasesModule {
    val getLocationUseCase = GetLocationUseCase(locationProvider)

    val getCurrentWeatherUseCase = GetCurrentWeatherUseCase(weatherRepository)
    val getForecastWeatherUseCase = GetForecastWeatherUseCase(weatherRepository)
}