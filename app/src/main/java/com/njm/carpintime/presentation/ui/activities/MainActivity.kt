package com.njm.carpintime.presentation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.njm.carpintime.R
import com.njm.carpintime.presentation.ui.fragments.CurrentWeatherFragment
import com.njm.carpintime.presentation.ui.fragments.ExtendedWeatherFragment
import com.njm.carpintime.presentation.ui.fragments.MapsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()


        var mainFragment = CurrentWeatherFragment()

        var mapsFragment = MapsFragment()

        var extendedWeatherFragment = ExtendedWeatherFragment()

        fragmentTransaction.add(R.id.currentWeather_fragment_container, mainFragment)
        fragmentTransaction.add(R.id.geolocation_fragment_container, mapsFragment)
        fragmentTransaction.add(R.id.extendedWeather_fragment_container, extendedWeatherFragment)
        fragmentTransaction.commit()

    }

}