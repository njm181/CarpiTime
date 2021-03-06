package com.njm.carpintime.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.njm.carpintime.R
import com.njm.carpintime.domain.model.GeoData
import com.njm.carpintime.presentation.viewModels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

}
//    private val weatherViewModel by activityViewModels<WeatherViewModel>()
//    private var dataResult: Boolean = false
//    private lateinit var geoData: GeoData
/*weatherViewModel.getData(LAT, LONG)
    weatherViewModel.getDataResponse().observe(this, Observer {
        dataResult = it
    })

    weatherViewModel.getGeoDataResponse().observe(this, Observer {
        geoData = it
        geoData.hashCode()
    })*/
/*private fun initObservable(){
    weatherViewModel.getDataResponse().observe(this, Observer {
        dataResult = it
    })
}*/