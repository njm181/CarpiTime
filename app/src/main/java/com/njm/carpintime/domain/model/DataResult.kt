package com.njm.carpintime.domain.model

data class DataResult(
    var current: Current,
    var daily: List<Daily>,
    var hourly: List<Hourly>,
    var lat: Double,
    var lon: Double,
    var timezone: String,
    var timezone_offset: Int
)