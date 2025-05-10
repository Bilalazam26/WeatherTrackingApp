package com.bilalazzam.instabugweatherapp.domain.location

import android.content.Context
import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate

class GetLocationUseCase(
    private val locationProvider: LocationProvider
) {
    fun execute(
        context: Context,
        onSuccess: (LocationCoordinate) -> Unit,
        onError: (String) -> Unit
    ) {
        locationProvider.getCurrentLocation(context, onSuccess, onError)
    }
}