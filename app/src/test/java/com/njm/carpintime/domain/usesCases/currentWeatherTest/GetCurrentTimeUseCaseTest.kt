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
class GetCurrentTimeUseCaseTest {

    @Test
    fun getTimeFromTimeUnix(){
        var date = 1599421387
        val parseTimeUnixToTime = Date(date * 1000L)
        val currentTime = SimpleDateFormat("HH:mm")
        currentTime.timeZone = TimeZone.getTimeZone("GMT-3")
        var hora = currentTime.format(parseTimeUnixToTime)
        Assert.assertEquals("16:43", hora)
    }

}