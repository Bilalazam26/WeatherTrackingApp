package com.bilalazzam.instabugweatherapp.data.location

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.bilalazzam.instabugweatherapp.domain.location.LocationProvider
import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate

class LocationProviderImpl : LocationProvider {
    override fun getCurrentLocation(
        context: Context,
        onSuccess: (LocationCoordinate) -> Unit,
        onError: (String) -> Unit
    ) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val listener = createLocationListener(locationManager, onSuccess, onError)
        val providers = getLocationProviders()

        for (provider in providers) {
            if (tryGetLocationFromProvider(
                    locationManager,
                    provider,
                    listener,
                    onSuccess,
                    onError
                )
            ) return
        }

        onError("No available location providers")
    }

    private fun getLocationProviders(): List<String> {
        return listOf(
            LocationManager.NETWORK_PROVIDER,
            LocationManager.GPS_PROVIDER,
            LocationManager.PASSIVE_PROVIDER
        )
    }

    private fun tryGetLocationFromProvider(
        locationManager: LocationManager,
        provider: String,
        listener: LocationListener,
        onSuccess: (LocationCoordinate) -> Unit,
        onError: (String) -> Unit
    ): Boolean {
        if (!locationManager.isProviderEnabled(provider)) return false

        return try {
            val lastKnown = locationManager.getLastKnownLocation(provider)
            if (lastKnown != null) {
                onSuccess(
                    LocationCoordinate(
                        latitude = lastKnown.latitude,
                        longitude = lastKnown.longitude
                    )
                )
                true
            } else {
                locationManager.requestLocationUpdates(provider, 0L, 0f, listener)
                true
            }
        } catch (exception: SecurityException) {
            onError(exception.message.toString())
            false
        }
    }

    private fun createLocationListener(
        locationManager: LocationManager,
        onSuccess: (LocationCoordinate) -> Unit,
        onError: (String) -> Unit
    ): LocationListener {
        return object : LocationListener {
            override fun onLocationChanged(location: Location) {
                onSuccess(
                    LocationCoordinate(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )
                )
                locationManager.removeUpdates(this)
            }

            override fun onProviderDisabled(provider: String) {
                onError("Location provider disabled: $provider")
            }

            override fun onProviderEnabled(provider: String) {}
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        }
    }
}