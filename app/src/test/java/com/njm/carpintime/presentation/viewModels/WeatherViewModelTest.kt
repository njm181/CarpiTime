package com.njm.carpintime.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import com.njm.carpintime.domain.usesCases.GetDataUseCase
import com.njm.carpintime.domain.usesCases.GetDataUseCaseTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Nicolas Molina 16/8/2020
 */

@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest{

    @Mock
    private var getDataUseCase: GetDataUseCase? = null

    @Mock
    private var state: SavedStateHandle? = null

    @InjectMocks
    private var weatherViewModel: WeatherViewModel? = null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        //weatherViewModel = WeatherViewModel(getDataUseCase!!)
    }

    @Test
    fun verificarEjecucionGetData(){
        weatherViewModel?.getData(LAT, LONG)
        verify(getDataUseCase, times(1))?.getData(LAT, LONG)

    }

    @Test
    fun ejecutarConParametrosEnCero(){

    }


    companion object{
        const val LAT: Double = -34.591825
        const val LONG: Double = -58.497258
    }

}