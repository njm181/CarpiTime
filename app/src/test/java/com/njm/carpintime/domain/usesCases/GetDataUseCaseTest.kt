package com.njm.carpintime.domain.usesCases

import com.njm.carpintime.domain.model.DataResult
import com.njm.carpintime.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

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
        getDataUseCase?.getData(LAT, LONG)
        getDataUseCase?.buildUseCaseSingle()
        verify(weatherRepository, times(1))?.getData(LAT, LONG);
    }

    @Test
    fun resultadoRepositoryIsNotNull(){
        `when`(getDataUseCase?.buildUseCaseSingle()).thenReturn(data)
        var response = getDataUseCase?.buildUseCaseSingle()
        Assert.assertNotNull(response)
    }

    @Test
    fun resultadoConParametrosEnCero(){
        `when`(weatherRepository?.getData(0.0, 0.0)).thenReturn(null)
        var response = getDataUseCase?.buildUseCaseSingle()
        Assert.assertNull(response)
        //Assert.assertEquals(response, data)
        //Assert.assertNotNull(getDataUseCase?.getData(1.0,1.0))
    }


    companion object{
        const val LAT: Double = -34.591825
        const val LONG: Double = -58.497258
    }
}