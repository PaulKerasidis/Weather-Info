package com.paulkera.weatherinfo.data.repository

import com.paulkera.weatherinfo.data.mappers.toWeatherInfo
import com.paulkera.weatherinfo.data.remote.IsDay
import com.paulkera.weatherinfo.data.remote.WeatherApi
import com.paulkera.weatherinfo.domain.repository.WeatherRepository
import com.paulkera.weatherinfo.domain.util.Resource
import com.paulkera.weatherinfo.domain.weather.WeatherInfo
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class WeatherRepositoryImp @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getIsDay(lat: Double, long: Double): Resource<IsDay> {

        return try {
            Resource.Success(
                data = api.getIsDay(
                    lat = lat,
                    long = long
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }


    }
}