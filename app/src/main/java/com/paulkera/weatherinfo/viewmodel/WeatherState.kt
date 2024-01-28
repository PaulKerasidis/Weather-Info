package com.paulkera.weatherinfo.viewmodel

import com.paulkera.weatherinfo.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)