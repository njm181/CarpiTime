package com.njm.carpintime.data.repositoryImp

import com.njm.carpintime.data.service.remote.WeatherApiService
import com.njm.carpintime.domain.model.DataResult
import com.njm.carpintime.domain.repository.WeatherRepository
import com.njm.carpintime.domain.utils.APPID
import com.njm.carpintime.domain.utils.CELSIUS
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(private val weatherApiService: WeatherApiService): WeatherRepository {

    override fun getData(lat: Double, lon: Double): Single<DataResult> {
        return weatherApiService.getData(lat, lon, CELSIUS, APPID)
    }
}