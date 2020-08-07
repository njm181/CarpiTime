package com.njm.carpintime.domain.model

data class Weather(
    var description: String,
    var icon: String,
    var id: Int,
    var main: String
)