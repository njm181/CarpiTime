package com.njm.carpintime.presentation.viewModels

import com.njm.carpintime.data.service.remote.WeatherApiService
import com.njm.carpintime.domain.model.DataResult
import com.njm.carpintime.domain.repository.WeatherRepository
import com.njm.carpintime.domain.usesCases.GetDataUseCase
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by Nicolas Molina 13/8/2020
 */
@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest{


    @Mock
    private var weatherRepository: WeatherRepository? = null

    @Mock
    private var weaatherApiService: WeatherApiService? = null

    @InjectMocks
    private var getDataUseCase: GetDataUseCase? = null



    @Before
    @Throws(Exception::class)
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        `when`(weatherRepository?.getData(LAT, LONG)).thenReturn(Single.just(DataResult(null, null, 0.0, 0.0, "Argentina", 1)))
    }

    @Test
    fun deberiaSerSuccess(){
        getDataUseCase?.buildUseCaseSingle()
        verify(weatherRepository)?.getData(LAT, LONG)
    }



    companion object{
        const val LAT: Double = -34.591825
        const val LONG: Double = -58.497258
    }
}