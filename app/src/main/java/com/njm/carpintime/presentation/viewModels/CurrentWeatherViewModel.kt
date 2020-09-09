package com.njm.carpintime.presentation.viewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.njm.carpintime.domain.usesCases.currentWeather.GetCurrentTimeUseCase
import com.njm.carpintime.domain.usesCases.currentWeather.GetDayOfWeekUseCase
import com.njm.carpintime.domain.usesCases.currentWeather.SetStatusCurrentWeatherUseCase

/**
Created by Nicolas Molina 6/9/2020
 */
class CurrentWeatherViewModel @ViewModelInject constructor(@Assisted private val state: SavedStateHandle,
                                                           private var getDayOfWeekUseCase: GetDayOfWeekUseCase,
                                                           private var getCurrentTimeUseCase: GetCurrentTimeUseCase,
                                                           private var setStatusCurrentWeatherUseCase: SetStatusCurrentWeatherUseCase
): ViewModel(){

    //LiveData
    private var currentDayMutableLiveData: MutableLiveData<String> = MutableLiveData()
    private var currentTimeMutableLiveData: MutableLiveData<String> = MutableLiveData()
    private var statusCurrentWeatherMutableLiveData: MutableLiveData<String> = MutableLiveData()


    //UseCases
    fun getDayOfWeek(date: Int){
       currentDayMutableLiveData.value = getDayOfWeekUseCase.getDayOfWeek(date)
    }

    fun getCurrentTime(date: Int){
        currentTimeMutableLiveData.value = getCurrentTimeUseCase.currentTime(date)
    }

    fun setStatusCurrentWeather(status: String){
        statusCurrentWeatherMutableLiveData.value = setStatusCurrentWeatherUseCase.setStatusWeather(status)
    }

    //Responses
    fun getDayOfWeekResponse(): MutableLiveData<String> {
        return currentDayMutableLiveData
    }

    fun getCurrentTimeResponse(): MutableLiveData<String>{
        return currentTimeMutableLiveData
    }

    fun getStatusCurrentWeatherResponse(): MutableLiveData<String>{
        return statusCurrentWeatherMutableLiveData
    }

}
