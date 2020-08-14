package com.njm.carpintime.domain.usesCases

import com.njm.carpintime.domain.model.DataResult
import com.njm.carpintime.domain.repository.WeatherRepository
import com.njm.carpintime.domain.usesCases.base.SingleBaseUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class GetDataUseCase @Inject constructor(private val weatherRepository: WeatherRepository): SingleBaseUseCase<DataResult>(){


    private var lat: Double = 0.0
    private var lon: Double = 0.0

    fun getData(lat: Double, lon: Double){
        this.lat = lat
        this.lon = lon
    }

    override fun buildUseCaseSingle(): Single<DataResult> {
        return weatherRepository.getData(lat, lon)
    }

}