package com.paulkera.weatherinfo.data.remote

import com.squareup.moshi.Json

data class IsDay(
    val time: String,
    val interval: Int,
    @field:Json(name = "is_day")
    val isDay: Int
)
