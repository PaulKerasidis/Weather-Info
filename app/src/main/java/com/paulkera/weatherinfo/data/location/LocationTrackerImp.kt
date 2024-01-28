package com.paulkera.weatherinfo.data.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.paulkera.weatherinfo.domain.location.LocationTracker
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LocationTrackerImp @Inject constructor(
//    private val locationClient: FusedLocationProviderClient,
    @ApplicationContext
    private val context: Context
): LocationTracker {


    private val locationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    @Throws
    override suspend fun getCurrentLocation(): Flow<Location> {
        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000L)
            .setMinUpdateDistanceMeters(1000F)
            .build()
        return callbackFlow {
            if (!hasLocationPermission()) throw NoPermissionsException
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.lastLocation?.let {
                        launch {
                            send(it)
                        }
                    }
                }
            }

            locationClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())

            awaitClose {
                // No one listens to flow anymore
                locationClient.removeLocationUpdates(locationCallback)
            }
        }
    }

    override fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

}

object NoPermissionsException : Exception()


