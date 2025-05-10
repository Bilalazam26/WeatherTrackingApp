package com.bilalazzam.instabugweatherapp.di

import com.bilalazzam.instabugweatherapp.di.UseCasesModule.getCurrentWeatherUseCase
import com.bilalazzam.instabugweatherapp.di.UseCasesModule.getForecastWeatherUseCase
import com.bilalazzam.instabugweatherapp.di.UseCasesModule.getLocationUseCase
import com.bilalazzam.instabugweatherapp.ui.currentweather.CurrentWeatherViewModel
import com.bilalazzam.instabugweatherapp.ui.forecast.ForecastViewModel

object ViewModelModule {
    val currentWeatherViewModel = CurrentWeatherViewModel(
        getCurrentWeatherUseCase,
        getLocationUseCase
    )

    val forecastViewModel = ForecastViewModel(
        getForecastWeatherUseCase,
        getLocationUseCase
    )
}