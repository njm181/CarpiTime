package com.njm.carpintime.domain.usesCases.currentWeather

import com.njm.carpintime.domain.utils.*
import kotlinx.android.synthetic.main.fragment_current_weather.*
import javax.inject.Inject

class SetStatusCurrentWeatherUseCase @Inject constructor() {

    fun setStatusWeather(status: String): String {
         return when(status){
            CLOUDS -> NUBLADO
            THUNDERSTORM -> TORMENTA
            DRIZZLE -> LLOVIZNA
            RAIN -> LLUVIA
            CLEAR -> DESPEJADO
            else -> ""
        }
    }
}