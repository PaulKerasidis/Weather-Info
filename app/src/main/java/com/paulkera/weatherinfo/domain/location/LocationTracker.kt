package com.paulkera.weatherinfo.domain.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationTracker {
    suspend fun getCurrentLocation(): Flow<Location>
    fun hasLocationPermission(): Boolean
}