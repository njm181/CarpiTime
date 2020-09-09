package com.njm.carpintime.presentation.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.njm.carpintime.R
import com.njm.carpintime.domain.model.DataResult
import com.njm.carpintime.domain.model.GeoData
import com.njm.carpintime.domain.utils.LAT
import com.njm.carpintime.domain.utils.LONG
import com.njm.carpintime.presentation.ui.fragments.CurrentWeatherFragment
import com.njm.carpintime.presentation.ui.fragments.ExtendedWeatherFragment
import com.njm.carpintime.presentation.ui.fragments.MapsFragment
import com.njm.carpintime.presentation.viewModels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

     //by activityViewModels<WeatherViewModel>()
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var dataResult: DataResult
    private lateinit var geoData: GeoData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadViewModel()
        loadFragments()
        initObservable()
        getWeatherData()
        disallowScrollToMap()
    }

    private fun initObservable(){
        weatherViewModel.getDataResponse().observe(this, Observer { it ->
            dataResult = it
        })
    }
    private fun getWeatherData(){
        weatherViewModel.getData(LAT, LONG)
    }

    private fun loadViewModel(){
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
    }

    private fun loadFragments(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val mainFragment = CurrentWeatherFragment()
        val mapsFragment = MapsFragment()
        val extendedWeatherFragment = ExtendedWeatherFragment()

        fragmentTransaction.add(R.id.currentWeather_fragment_container, mainFragment)
        fragmentTransaction.add(R.id.geolocation_fragment_container, mapsFragment)
        fragmentTransaction.add(R.id.extendedWeather_fragment_container, extendedWeatherFragment)
        fragmentTransaction.commit()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun disallowScrollToMap(){
        transparent_image.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Disallow ScrollView to intercept touch events.
                    fragment_scroll_container.requestDisallowInterceptTouchEvent(true)
                    // Disable touch on transparent view
                    false
                }
                MotionEvent.ACTION_UP -> {
                    // Allow ScrollView to intercept touch events.
                    fragment_scroll_container.requestDisallowInterceptTouchEvent(false)
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    fragment_scroll_container.requestDisallowInterceptTouchEvent(true)
                    false
                }
                else -> true
            }
        }
    }

}