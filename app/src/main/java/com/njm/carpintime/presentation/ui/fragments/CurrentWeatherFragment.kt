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
import com.njm.carpintime.domain.model.Current
import com.njm.carpintime.domain.utils.*
import com.njm.carpintime.presentation.viewModels.CurrentWeatherViewModel
import com.njm.carpintime.presentation.viewModels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_current_weather.*
import java.text.DecimalFormat


@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {
    private val weatherViewModel by activityViewModels<WeatherViewModel>()
    private val currentWeatherViewModel by activityViewModels<CurrentWeatherViewModel>()
    private lateinit var ctx: Context
    var dayOrNight: Boolean = false

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
            setData(it)
        })

        currentWeatherViewModel.getCurrentTimeResponse().observe(this, Observer { time ->
            hora.text = time
        })

        currentWeatherViewModel.getDayOfWeekResponse().observe(this, Observer { dia ->
            nombre_dia.text = dia
        })

        weatherViewModel.parseCityNameResponse().observe(this, Observer {
            ciudad.text = it
        })

        currentWeatherViewModel.getStatusCurrentWeatherResponse().observe(this, Observer { status ->
            estado_clima.text = status
        })
    }

    private fun setData(current: Current) {
        currentWeatherViewModel.getDayOfWeek(current.dt)
        currentWeatherViewModel.getCurrentTime(current.dt)
        setTemperature(current.temp)
        for (it in current.weather){
            dayOrNight = setIconWeather(it.icon)
            card_current_weather.setBackgroundResource(findResource(it.main, null ,dayOrNight))
            currentWeatherViewModel.setStatusCurrentWeather(it.main)
        }
    }

    private fun setIconWeather(iconName: String): Boolean {
        imagen_clima.setImageResource(findResource(iconName, ICON, null))
        return isDayOrNight(iconName)

    }

    private fun isDayOrNight(check: String): Boolean{
        return check.contains(DAY)
    }

    private fun findResource(resourceName: String, type: String?, statusDay: Boolean?): Int {
        val resourceIdentifier: Int
        when {
            type != null -> {
                resourceIdentifier = resources.getIdentifier(
                    "$type$resourceName",
                    "drawable",
                    ctx.packageName
                )
            }
            statusDay!! -> {
                resourceIdentifier = resources.getIdentifier(
                    resourceName.toLowerCase()+DAY,
                    "drawable",
                    ctx.packageName
                )
            }
            else -> {
                resourceIdentifier = resources.getIdentifier(
                    resourceName.toLowerCase()+NIGHT,
                    "drawable",
                    ctx.packageName
                )
            }
        }
        return resourceIdentifier
    }

    private fun setTemperature(temperature: Double){
        val df = DecimalFormat("#.#")
        valor_temperatura.text = df.format(temperature).toString()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
    }
}

