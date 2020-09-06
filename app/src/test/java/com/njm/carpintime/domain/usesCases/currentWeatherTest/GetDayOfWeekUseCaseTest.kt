package com.njm.carpintime.domain.usesCases.currentWeatherTest

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.text.SimpleDateFormat
import java.util.*

/**
Created by Nicolas Molina 6/9/2020
 */
@RunWith(MockitoJUnitRunner.Silent::class)
class GetDayOfWeekUseCaseTest {


    @Test
    fun getDayOfWeek() {
        val date = 1599421387
        val parseTimeUnixToDate = Date(date * 1000L)
        val day = SimpleDateFormat("EEEE", Locale.US)
        day.timeZone = TimeZone.getTimeZone("GMT-3")
        var weekDay = day.format(parseTimeUnixToDate)

        weekDay = when (weekDay) {
            "Monday" -> "Lunes"
            "Tuesday" -> "Martes"
            "Wednesday" -> "Miercoles"
            "Thursday" -> "Jueves"
            "Friday" -> "Viernes"
            "Saturday" -> "Sábado"
            "Sunday" -> "Domingo"
            else -> ""
        }
        Assert.assertEquals("Domingo", weekDay)
    }
}