package com.paulkera.weatherinfo.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulkera.weatherinfo.data.location.NoPermissionsException
import com.paulkera.weatherinfo.domain.location.LocationTracker
import com.paulkera.weatherinfo.domain.repository.WeatherRepository
import com.paulkera.weatherinfo.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            try {
                locationTracker.getCurrentLocation().collect { location ->

                    when (val result =
                        repository.getWeatherData(location.latitude, location.longitude)) {
                        is Resource.Success -> {
                            state = state.copy(
                                weatherInfo = result.data,
                                isLoading = false,
                                error = null
                            )
                        }

                        is Resource.Error -> {
                            state = state.copy(
                                weatherInfo = null,
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }
            } catch (e: NoPermissionsException) {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }

        }
    }
}


