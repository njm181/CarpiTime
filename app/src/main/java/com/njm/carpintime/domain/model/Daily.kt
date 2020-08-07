package com.njm.carpintime.domain.model

data class Daily(
    var clouds: Int,
    var dew_point: Double,
    var dt: Int,
    var feels_like: FeelsLike,
    var humidity: Int,
    var pop: Double,
    var pressure: Int,
    var rain: Double,
    var sunrise: Int,
    var sunset: Int,
    var temp: Temp,
    var uvi: Double,
    var weather: List<WeatherX>,
    var wind_deg: Int,
    var wind_speed: Double
)