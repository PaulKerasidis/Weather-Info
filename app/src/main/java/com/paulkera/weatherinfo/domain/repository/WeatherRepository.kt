package com.paulkera.weatherinfo.domain.repository

import com.paulkera.weatherinfo.data.remote.IsDay
import com.paulkera.weatherinfo.domain.util.Resource
import com.paulkera.weatherinfo.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>

    suspend fun getIsDay(lat: Double, long: Double): Resource<IsDay>
}