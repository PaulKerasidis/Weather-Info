package com.paulkera.weatherinfo.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulkera.weatherinfo.data.location.NoPermissionsException
import com.paulkera.weatherinfo.domain.location.LocationTracker
import com.paulkera.weatherinfo.domain.repository.WeatherRepository
import com.paulkera.weatherinfo.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val _state: MutableStateFlow<WeatherState> = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()

    fun reLoadWeatherInfo(){

            loadWeatherInfo()

    }

    fun loadWeatherInfo() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
                error = null
            )
            try {
                locationTracker.getCurrentLocation().collect { location ->

                    when (val result =
                        repository.getWeatherData(location.latitude, location.longitude)) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                weatherInfo = result.data,
                                isLoading = false,
                                error = null
                            )
                        }

                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                weatherInfo = null,
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }
            } catch (e: NoPermissionsException) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }

        }
    }
}


