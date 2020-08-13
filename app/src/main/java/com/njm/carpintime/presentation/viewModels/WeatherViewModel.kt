package com.njm.carpintime.presentation.viewModels

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.njm.carpintime.domain.model.Current
import com.njm.carpintime.domain.model.DataResult
import com.njm.carpintime.domain.model.GeoData
import com.njm.carpintime.domain.model.Hourly
import com.njm.carpintime.domain.usesCases.GetDataUseCase
import com.njm.carpintime.domain.usesCases.base.DisposableManager

class WeatherViewModel @ViewModelInject constructor(@Assisted private val state: SavedStateHandle,
                                                    private var getDataUseCase: GetDataUseCase): ViewModel(){

    private var dataResultMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var currentMutableLiveData: MutableLiveData<Current> = MutableLiveData()
    private var hourlyMutableLiveData: MutableLiveData<List<Hourly>> = MutableLiveData()
    private var geoDataMutableLiveData: MutableLiveData<GeoData> = MutableLiveData()


    fun getData(lat: Double, lon: Double) {
        getDataUseCase.getData(lat, lon)
        getDataUseCase.execute(
            onSuccess = {
                onSuccess(it)
            },
            onError = {
                onError(it)
            }
        )
    }

    private fun onSuccess(response: DataResult) {
        dataResultMutableLiveData.value = true
        currentMutableLiveData.value = response.current
        hourlyMutableLiveData.value = response.hourly
        geoDataMutableLiveData.value = GeoData(response.lat, response.lon, response.timezone, response.timezone_offset)
    }

    private fun onError(error: Throwable) {
        Log.e("ERROR", "get data failure: "+ error.message)
        dataResultMutableLiveData.value = false
        currentMutableLiveData.value = null
        hourlyMutableLiveData.value = null
        geoDataMutableLiveData.value = null
    }

    fun getDataResponse(): MutableLiveData<Boolean> {
        return dataResultMutableLiveData
    }

    fun getCurrentResponse(): MutableLiveData<Current> {
        return currentMutableLiveData
    }

    fun getHourlyResponse(): MutableLiveData<List<Hourly>> {
        return hourlyMutableLiveData
    }

    fun getGeoDataResponse(): MutableLiveData<GeoData> {
        return geoDataMutableLiveData
    }

    override fun onCleared() {
        super.onCleared()
        DisposableManager.disposeDisposable()

    }

}