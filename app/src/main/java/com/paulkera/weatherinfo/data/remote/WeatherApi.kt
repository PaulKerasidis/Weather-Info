package com.paulkera.weatherinfo.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl&timezone=auto")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double ,
        @Query("longitude") long: Double
    ): WeatherDto

    @GET("v1/forecast?current=is_day&timezone=auto")
    suspend fun getIsDay(
        @Query("latitude") lat: Double ,
        @Query("longitude") long: Double
    ): IsDay
}