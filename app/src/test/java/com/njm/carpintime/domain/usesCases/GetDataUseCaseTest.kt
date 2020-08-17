package com.njm.carpintime.domain.usesCases

import com.njm.carpintime.domain.model.DataResult
import com.njm.carpintime.domain.repository.WeatherRepository
import com.njm.carpintime.domain.utils.excepction.ParametersZeroException
import io.reactivex.rxjava3.core.Single
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

/**
 * Created by nmolina on 14/8/2020
 */
@RunWith(MockitoJUnitRunner.Silent::class)
class GetDataUseCaseTest{

    @InjectMocks
    private var getDataUseCase: GetDataUseCase? = null

    @Mock
    private var weatherRepository: WeatherRepository? = null

    @Mock
    var data:Single<DataResult>? = null


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        getDataUseCase = GetDataUseCase(weatherRepository!!)
    }

    @Test
    fun verificarUsoDelRepository(){
        //given
        getDataUseCase?.getData(LAT, LONG)
        //when
        getDataUseCase?.buildUseCaseSingle()
        //then
        verify(weatherRepository, times(1))?.getData(LAT, LONG);
    }

    @Test
    fun resultadoRepositoryIsNotNull(){
        //given
        given(getDataUseCase?.buildUseCaseSingle()).willReturn(data)
        //when
        var response = getDataUseCase?.buildUseCaseSingle()
        //then
        Assert.assertNotNull(response)
    }

    @Test
    fun resultadoConParametrosEnCero(){
        //given
        given(weatherRepository?.getData(0.0, 0.0)).willReturn(null)
        //when
        var response = getDataUseCase?.buildUseCaseSingle()
        //then
        Assert.assertNull(response)
    }

    @Test
    fun ejecutarConParametrosEnCero(){
        //given
        var ex: Exception? = null
        //when
        try {
            getDataUseCase?.getData(0.0,0.0)
        }catch (e: ParametersZeroException){
            print(e.message)
            ex = e
        }
        //then
        assertNotNull(ex)
    }

    companion object{
        const val LAT: Double = -34.591825
        const val LONG: Double = -58.497258
    }
}