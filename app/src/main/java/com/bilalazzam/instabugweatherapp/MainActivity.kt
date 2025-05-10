package com.bilalazzam.instabugweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bilalazzam.instabugweatherapp.di.DataModule
import com.bilalazzam.instabugweatherapp.ui.AppScreens
import com.bilalazzam.instabugweatherapp.ui.currentweather.CurrentWeatherScreen
import com.bilalazzam.instabugweatherapp.ui.forecast.ForecastScreen
import com.bilalazzam.instabugweatherapp.ui.theme.InstabugWeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        DataModule.initialize(this)
        setContent {
            var currentScreen by remember { mutableStateOf<AppScreens>(AppScreens.CurrentWeatherScreen) }

            InstabugWeatherAppTheme {
                when (currentScreen) {
                    AppScreens.CurrentWeatherScreen -> CurrentWeatherScreen(
                        onNavigationToForecastScreen = {
                            currentScreen = AppScreens.ForecastScreen
                        }
                    )

                    AppScreens.ForecastScreen -> ForecastScreen(
                        onNavigateBack = {
                            currentScreen = AppScreens.CurrentWeatherScreen
                        }
                    )
                }
            }
        }
    }
}