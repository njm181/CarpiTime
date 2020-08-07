package com.njm.carpintime.domain.model

data class Hourly(
    var clouds: Int,
    var dew_point: Double,
    var dt: Int,
    var feels_like: Double,
    var humidity: Int,
    var pop: Double,
    var pressure: Int,
    var rain: Rain,
    var temp: Double,
    var visibility: Int,
    var weather: List<WeatherXX>,
    var wind_deg: Int,
    var wind_speed: Double
)