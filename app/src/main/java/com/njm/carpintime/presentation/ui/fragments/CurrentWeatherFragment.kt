package com.njm.carpintime.presentation.ui.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.njm.carpintime.R
import com.njm.carpintime.domain.model.Weather
import com.njm.carpintime.domain.utils.*
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
            currentWeatherViewModel.getDayOfWeekUseCase(it.dt)

            currentWeatherViewModel.getDayOfWeekResponse().observe(this, Observer { dia ->
                nombre_dia.text = dia
            })
            currentWeatherViewModel.getCurrentTimeUseCase(it.dt)
            currentWeatherViewModel.getCurrentTimeResponse().observe(this, Observer { time ->
                hora.text = time
            })
            setIconAndBackgroundColor(it.weather)
        })
    }

    private fun setIconAndBackgroundColor(weather: List<Weather>) {
        var dayOrNight: Boolean
        for (it in weather){
            dayOrNight = setIconWeather(it.icon)
            setBackgroundCard(it.main, dayOrNight)
        }

    }

    private fun setBackgroundCard(mainDescription: String, statusDay: Boolean) {
        if(statusDay){
        }else{
            card_current_weather.setBackgroundResource(findResource(mainDescription, null, statusDay))
        }
    }

    private fun setIconWeather(iconName: String): Boolean {
        imagen_clima.setImageResource(findResource(iconName, ICON, null))
        return isDayOrNight(iconName)

    }

    private fun isDayOrNight(check: String): Boolean{
        return check.contains("d")
    }

    /**agregar un parametro mas que filtre si es dia o noche d o n para adjuntar a lal ruta absoluta de la imagen*/
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
    }
}

