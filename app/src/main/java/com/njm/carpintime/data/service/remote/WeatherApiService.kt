package com.njm.carpintime.data.service.remote

import com.njm.carpintime.domain.model.DataResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("onecall")
    fun getData(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("units")celsius: String, @Query("appid") key: String): Single<DataResult>
}