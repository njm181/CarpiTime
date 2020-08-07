package com.njm.carpintime.domain.usesCases

import com.njm.carpintime.data.repositoryImp.WeatherRepositoryImp
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val weatherRepositoryImp: WeatherRepositoryImp){

    private var lat: Double = 0.0
    private var lon: Double = 0.0

    //Aca tengo que hacer la logica de negocio, el rx va en el viewmodel, tratar de que solo atrape el valor, aca deberia ir todo lo pesado

    fun getData(lat: Double, lon: Double){
        weatherRepositoryImp.getData(lat, lon)
    }
}