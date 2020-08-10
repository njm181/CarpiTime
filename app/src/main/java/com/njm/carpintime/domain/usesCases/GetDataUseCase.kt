package com.njm.carpintime.domain.usesCases

import com.njm.carpintime.domain.model.DataResult
import com.njm.carpintime.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class GetDataUseCase @Inject constructor(private val WeatherRepository: WeatherRepository){


    fun getData(lat: Double, lon: Double): Single<DataResult> {
        return WeatherRepository.getData(lat, lon)
    }

}