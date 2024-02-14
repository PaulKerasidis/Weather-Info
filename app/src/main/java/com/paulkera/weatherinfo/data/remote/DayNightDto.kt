package com.paulkera.weatherinfo.data.remote

import com.squareup.moshi.Json

data class DayNightDto(
    @field:Json(name = "current")
    val dayNight: IsDay
)
