package com.njm.carpintime.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.njm.carpintime.R
import com.njm.carpintime.presentation.viewModels.CurrentWeatherViewModel
import com.njm.carpintime.presentation.viewModels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_current_weather.*

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {
    private val weatherViewModel by activityViewModels<WeatherViewModel>()
    private val currentWeatherViewModel by activityViewModels<CurrentWeatherViewModel>()
    private lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservable()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    private fun initObservable(){
        weatherViewModel.getCurrentResponse().observe(this, androidx.lifecycle.Observer {
            currentWeatherViewModel.getDayOfWeek(it.dt).toString()
            currentWeatherViewModel.getDayOfWeekResponse().observe(this, Observer { dia ->
                nombre_dia.text = dia
            })
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
    }
}

