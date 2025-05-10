package com.bilalazzam.instabugweatherapp.domain.location

import android.content.Context
import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate

interface LocationProvider {
    fun getCurrentLocation(
        context: Context,
        onSuccess: (LocationCoordinate) -> Unit,
        onError: (String) -> Unit
    )

}