package com.njm.carpintime.domain.usesCases.currentWeather

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
Created by Nicolas Molina 6/9/2020
 */
class GetCurrentTimeUseCase @Inject constructor() {

    fun currentTime(date: Int): String {
        val parseTimeUnixToTime = Date(date * 1000L)
        val currentTime = SimpleDateFormat("HH:mm")
        currentTime.timeZone = TimeZone.getTimeZone("GMT-3")
        return currentTime.format(parseTimeUnixToTime)
    }
}