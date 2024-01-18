package com.paulkera.weatherinfo.data.repository

import com.paulkera.weatherinfo.data.mappers.toWeatherInfo
import com.paulkera.weatherinfo.data.remote.WeatherApi
import com.paulkera.weatherinfo.domain.repository.WeatherRepository
import com.paulkera.weatherinfo.domain.util.Resource
import com.paulkera.weatherinfo.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val api: WeatherApi
):WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e:Exception){
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error accured.")
        }
    }
}