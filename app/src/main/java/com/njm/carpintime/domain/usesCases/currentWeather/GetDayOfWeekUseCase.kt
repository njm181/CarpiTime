package com.njm.carpintime.domain.usesCases.currentWeather

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
Created by Nicolas Molina 6/9/2020
 */
class GetDayOfWeekUseCase @Inject constructor() {

    fun getDayOfWeek(date: Int): String {
        val parseUnixToDate = Date(date * 1000L)
        val day = SimpleDateFormat("EEEE", Locale.US)
        day.timeZone = TimeZone.getTimeZone("GMT-3")
        var weekDay = day.format(parseUnixToDate)

        weekDay = when(weekDay){
            "Monday" -> "Lunes"
            "Tuesday" -> "Martes"
            "Wednesday" -> "Miercoles"
            "Thursday" -> "Jueves"
            "Friday" -> "Viernes"
            "Saturday" -> "Sábado"
            "Sunday" -> "Domingo"
            else -> ""
        }
        return weekDay
    }
}