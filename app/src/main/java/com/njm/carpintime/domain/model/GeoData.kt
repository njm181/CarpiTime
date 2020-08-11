package com.njm.carpintime.domain.model

data class GeoData (
    var lat: Double,
    var lon: Double,
    var timezone: String,
    var timezone_offset: Int
)