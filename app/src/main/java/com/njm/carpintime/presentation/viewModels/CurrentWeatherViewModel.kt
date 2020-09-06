package com.njm.carpintime.presentation.viewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.njm.carpintime.domain.usesCases.currentWeather.GetDayOfWeekUseCase

/**
Created by Nicolas Molina 6/9/2020
 */
class CurrentWeatherViewModel @ViewModelInject constructor(@Assisted private val state: SavedStateHandle,
                                                           private var getDayOfWeekUseCase: GetDayOfWeekUseCase
): ViewModel(){

    private var currentDayMutableLiveData: MutableLiveData<String> = MutableLiveData()


    fun getDayOfWeek(date: Int){
       currentDayMutableLiveData.value = getDayOfWeekUseCase.getDayOfWeek(date)
    }

    fun getDayOfWeekResponse(): MutableLiveData<String> {
        return currentDayMutableLiveData
    }
}
