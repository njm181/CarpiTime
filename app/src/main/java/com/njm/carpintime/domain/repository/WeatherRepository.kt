package com.njm.carpintime.domain.repository

import com.njm.carpintime.domain.model.DataResult
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    fun getData(lat: Double, lon: Double): Single<DataResult>
}