package com.paulkera.weatherinfo.domain.weather

import androidx.annotation.DrawableRes
import com.paulkera.weatherinfo.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconResDay: Int,
    @DrawableRes val iconResNight: Int
) {
    object ClearSky : WeatherType(
        weatherDesc = "Clear sky",
        iconResDay = R.drawable.day_clear,
        iconResNight = R.drawable.night_half_moon_clear
    )
    object MainlyClear : WeatherType(
        weatherDesc = "Mainly clear",
        iconResDay = R.drawable.day_partial_cloud,
        iconResNight = R.drawable.night_half_moon_partial_cloud
    )
    object PartlyCloudy : WeatherType(
        weatherDesc = "Partly cloudy",
        iconResDay = R.drawable.day_partial_cloud,
        iconResNight = R.drawable.night_half_moon_partial_cloud
    )
    object Overcast : WeatherType(
        weatherDesc = "Overcast",
        iconResDay = R.drawable.overcast,
        iconResNight = R.drawable.overcast
    )
    object Foggy : WeatherType(
        weatherDesc = "Foggy",
        iconResDay = R.drawable.fog,
        iconResNight = R.drawable.fog

    )
    object DepositingRimeFog : WeatherType(
        weatherDesc = "Depositing rime fog",
        iconResDay = R.drawable.mist,
        iconResNight = R.drawable.mist
    )
    object LightDrizzle : WeatherType(
        weatherDesc = "Light drizzle",
        iconResDay = R.drawable.rain,
        iconResNight = R.drawable.rain
    )
    object ModerateDrizzle : WeatherType(
        weatherDesc = "Moderate drizzle",
        iconResDay = R.drawable.rain,
        iconResNight = R.drawable.rain
    )
    object DenseDrizzle : WeatherType(
        weatherDesc = "Dense drizzle",
        iconResDay = R.drawable.rain,
        iconResNight = R.drawable.rain
    )
    object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Slight freezing drizzle",
        iconResDay = R.drawable.sleet,
        iconResNight = R.drawable.sleet
    )
    object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Dense freezing drizzle",
        iconResDay = R.drawable.snow,
        iconResNight = R.drawable.snow
    )
    object SlightRain : WeatherType(
        weatherDesc = "Slight rain",
        iconResDay = R.drawable.rain,
        iconResNight = R.drawable.rain
    )
    object ModerateRain : WeatherType(
        weatherDesc = "Rainy",
        iconResDay = R.drawable.rain,
        iconResNight = R.drawable.rain
    )
    object HeavyRain : WeatherType(
        weatherDesc = "Heavy rain",
        iconResDay = R.drawable.rain,
        iconResNight = R.drawable.rain
    )
    object HeavyFreezingRain: WeatherType(
        weatherDesc = "Heavy freezing rain",
        iconResDay = R.drawable.sleet,
        iconResNight = R.drawable.sleet
    )
    object SlightSnowFall: WeatherType(
        weatherDesc = "Slight snow fall",
        iconResDay = R.drawable.snow,
        iconResNight = R.drawable.snow
    )
    object ModerateSnowFall: WeatherType(
        weatherDesc = "Moderate snow fall",
        iconResDay =  R.drawable.snow,
        iconResNight =  R.drawable.snow
    )
    object HeavySnowFall: WeatherType(
        weatherDesc = "Heavy snow fall",
        iconResDay =  R.drawable.snow,
        iconResNight =  R.drawable.snow
    )
    object SnowGrains: WeatherType(
        weatherDesc = "Snow grains",
        iconResDay =  R.drawable.snow,
        iconResNight =  R.drawable.snow
    )
    object SlightRainShowers: WeatherType(
        weatherDesc = "Slight rain showers",
        iconResDay = R.drawable.rain,
        iconResNight = R.drawable.rain
    )
    object ModerateRainShowers: WeatherType(
        weatherDesc = "Moderate rain showers",
        iconResDay = R.drawable.rain,
        iconResNight = R.drawable.rain
    )
    object ViolentRainShowers: WeatherType(
        weatherDesc = "Violent rain showers",
        iconResDay = R.drawable.rain,
        iconResNight = R.drawable.rain
    )
    object SlightSnowShowers: WeatherType(
        weatherDesc = "Light snow showers",
        iconResDay =  R.drawable.snow,
        iconResNight =  R.drawable.snow
    )
    object HeavySnowShowers: WeatherType(
        weatherDesc = "Heavy snow showers",
        iconResDay =  R.drawable.snow,
        iconResNight =  R.drawable.snow
    )
    object ModerateThunderstorm: WeatherType(
        weatherDesc = "Moderate thunderstorm",
        iconResDay = R.drawable.thunder,
        iconResNight = R.drawable.thunder
    )
    object SlightHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with slight hail",
        iconResDay = R.drawable.rain_thunder,
        iconResNight = R.drawable.rain_thunder
    )
    object HeavyHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with heavy hail",
        iconResDay = R.drawable.rain_thunder,
        iconResNight = R.drawable.rain_thunder
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}