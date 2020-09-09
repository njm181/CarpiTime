package com.njm.carpintime.domain.usesCases.weather

import kotlinx.android.synthetic.main.fragment_current_weather.*
import javax.inject.Inject

class ParseCityNameUseCase @Inject constructor() {

    fun parseCityName(cityName: String): String {
        val city = cityName.split("America/Argentina/")

        return if(city[1].contains("_")){
            val cityFormat = city[1].split("_")
            val firstPart = cityFormat[0]
            val secondPart = cityFormat[1]
            "$firstPart $secondPart"
        }else{
            city[1]
        }
    }
}