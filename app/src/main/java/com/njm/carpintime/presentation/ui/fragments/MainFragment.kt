package com.njm.carpintime.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.njm.carpintime.R
import com.njm.carpintime.domain.model.DataResult
import com.njm.carpintime.domain.model.GeoData
import com.njm.carpintime.domain.utils.LAT
import com.njm.carpintime.domain.utils.LONG
import com.njm.carpintime.presentation.viewModels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val weatherViewModel by activityViewModels<WeatherViewModel>()
    private var dataResult: Boolean = false
    private lateinit var geoData: GeoData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*weatherViewModel.getData(LAT, LONG)
        weatherViewModel.getDataResponse().observe(this, Observer {
            dataResult = it
        })

        weatherViewModel.getGeoDataResponse().observe(this, Observer {
            geoData = it
            geoData.hashCode()
        })*/

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun initObservable(){
        weatherViewModel.getDataResponse().observe(this, Observer {
            dataResult = it
        })
    }



}