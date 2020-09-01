package com.njm.carpintime.presentation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.njm.carpintime.R
import com.njm.carpintime.presentation.ui.fragments.MainFragment
import com.njm.carpintime.presentation.ui.fragments.MapsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()


        var mainFragment = MainFragment()

        var mapsFragment = MapsFragment()

        fragmentTransaction.add(R.id.main_fragment_container, mainFragment)
        fragmentTransaction.add(R.id.geolocation_fragment_container, mapsFragment)
        fragmentTransaction.commit()

    }

}