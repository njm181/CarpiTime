package com.njm.carpintime.domain.usesCases

import com.njm.carpintime.domain.model.DataResult
import com.njm.carpintime.domain.repository.WeatherRepository
import com.njm.carpintime.domain.usesCases.base.SingleBaseUseCase
import com.njm.carpintime.domain.utils.excepction.ParametersZeroException
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class GetDataUseCase @Inject constructor(private val weatherRepository: WeatherRepository): SingleBaseUseCase<DataResult>(){

    private var lat: Double = 0.0
    private var lon: Double = 0.0

    @Throws(ParametersZeroException::class)
    fun getData(lat: Double, lon: Double){
        if(lat == 0.0 || lon == 0.0){
            throw ParametersZeroException()
        }else{
            this.lat = lat
            this.lon = lon
        }
    }

    override fun buildUseCaseSingle(): Single<DataResult> {
        return weatherRepository.getData(lat, lon)
    }

}