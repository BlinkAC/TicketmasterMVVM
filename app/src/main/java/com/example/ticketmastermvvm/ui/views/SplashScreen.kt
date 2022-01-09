package com.example.ticketmastermvvm.ui.views

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.ticketmastermvvm.databinding.ActivitySplashScreenBinding
import com.google.android.gms.location.*
import com.squareup.picasso.Picasso

class SplashScreen : AppCompatActivity() {
    //Last known location
    lateinit var mfusedlocation: FusedLocationProviderClient
    private var myRequestCode = 1010
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val posterImage: ImageView = binding.posterSplash
        mfusedlocation = LocationServices.getFusedLocationProviderClient(this)

        Picasso.get().load("https://images.pexels.com/photos/2147029/pexels-photo-2147029.jpeg").into(posterImage)
        getLastLocation()
    }

    // 1. Location permission --> deny
    // 2. Location denied through setting
    // 3. gps off
    // 4. permission ok
    //

    fun getLastLocation() {
        if (checkPermission()) {
            if (locationEnabled()) {
                mfusedlocation.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        newLocation()
                    } else {
                        Handler(Looper.getMainLooper()).postDelayed({
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("latitude", location.latitude.toString())
                            intent.putExtra("longitude", location.longitude.toString())
                            startActivity(intent)
                            finish()

                        }, 2000)
                    }
                }
            } else {
                Toast.makeText(this, "Porfavor encienda su GPS", Toast.LENGTH_LONG).show()
            }
        } else {
            requestPermission()
        }
    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun locationEnabled(): Boolean {
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    }

    @SuppressLint("MissingPermission")
    private fun newLocation() {
        var locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval=0
        locationRequest.fastestInterval=0
        locationRequest.numUpdates=1
        mfusedlocation=LocationServices.getFusedLocationProviderClient(this)
        mfusedlocation.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())

    }
    private val locationCallback= object: LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation : Location = p0.lastLocation
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), myRequestCode
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == myRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            }
        }
    }
}