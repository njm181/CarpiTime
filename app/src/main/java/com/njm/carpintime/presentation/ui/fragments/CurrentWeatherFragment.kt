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
            setData(it)
        })
        weatherViewModel.getDataResponse().observe(this, Observer {
            parseCityName(it.timezone)
        })
    }

    private fun parseCityName(timezone: String) {
        val city = timezone.split("America/Argentina/")

        if(city[1].contains("_")){
            val cityFormat = city[1].split("_")
            val firstPart = cityFormat[0]
            val secondPart = cityFormat[1]
            ciudad.text = "$firstPart $secondPart"
        }else{
            ciudad.text = city[1].toString()
        }
    }

    private fun setData(current: Current) {
        var dayOrNight: Boolean
        val df = DecimalFormat("#.#")
        valor_temperatura.text = df.format(current.temp).toString()
        for (it in current.weather){
            dayOrNight = setIconWeather(it.icon)
            card_current_weather.setBackgroundResource(findResource(it.main, null ,dayOrNight))
            setStatusWeather(it.main)
        }
    }

    private fun setStatusWeather(status: String) {
        var statusWeather: String = when(status){
            CLOUDS -> NUBLADO
            THUNDERSTORM -> TORMENTA
            DRIZZLE -> LLOVIZNA
            RAIN -> LLUVIA
            CLEAR -> DESPEJADO
            else -> ""
        }
        estado_clima.text = statusWeather
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
    }
}

