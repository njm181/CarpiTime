package com.njm.carpintime.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import com.njm.carpintime.domain.model.DataResult
import com.njm.carpintime.domain.usesCases.GetDataUseCase
import com.njm.carpintime.domain.usesCases.GetDataUseCaseTest
import com.njm.carpintime.domain.utils.excepction.ParametersZeroException
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.BDDMockito.given
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

/**
 * Created by Nicolas Molina 16/8/2020
 */

@RunWith(MockitoJUnitRunner.Silent::class)
class WeatherViewModelTest{

    @Mock
    private var getDataUseCase: GetDataUseCase? = null

    @Mock
    private var state: SavedStateHandle? = null

    @Mock
    var data: Single<DataResult>? = null

    @InjectMocks
    private var weatherViewModel: WeatherViewModel? = null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun verificarEjecucionGetData(){
        //given
        given(getDataUseCase?.buildUseCaseSingle()).willReturn(data)
        //when
        weatherViewModel?.getData(LAT, LONG)
        //then
        verify(getDataUseCase, times(1))?.getData(LAT, LONG)
    }

    @Test
    fun verificarTipoDatoRetornaNoEsNull(){
        //given
        var tipoData: Single<DataResult>? = null
        given(getDataUseCase?.buildUseCaseSingle()).willReturn(data)
        //then
        tipoData = getDataUseCase?.buildUseCaseSingle()
        //then
        assertNotNull(tipoData)
    }

    @Test
    fun verificarTipoDatoRetorna(){
        //given
        given(getDataUseCase?.buildUseCaseSingle()).willReturn(data)
        //then
        var tipoData: Single<DataResult>? = getDataUseCase?.buildUseCaseSingle()
        //then
        assertEquals(tipoData, data)
    }


    companion object{
        const val LAT: Double = -34.591825
        const val LONG: Double = -58.497258
    }

}