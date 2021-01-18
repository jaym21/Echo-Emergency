package com.example.echoemergency

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.telephony.SmsManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_alert.*
import java.util.*
import java.util.jar.Manifest

class Alert : Fragment() {

    //it is just an int that must be unique so it can be any number
    private var PERMISSION_ID = 1000
    private var requestSendSms = 2

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_alert, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)

        btnAlert.setOnClickListener {
            getLocation()
        }
    }


    //to get the last location
    private fun getLocation() {
        //first we need to check permission
        if (checkPermission()) {

            fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                var location = it.result
                if (location != null){
                    //location.latitude will give latitude and location.longitude will give longitude
                    // now for sending sms with longitude and latitude
                    if (ActivityCompat.checkSelfPermission(context!!, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        val number = "23224"
                        val text = "whbdhubd"
                        SmsManager.getDefault().sendTextMessage(number,null,text,null,null)
                    }else{
                        ActivityCompat.checkSelfPermission(context!!, android.Manifest.permission.SEND_SMS)
                    }
                } else{
                    //if the location is null we will get new location of user
                    newLocationData()
                }
            }

        }else {
            requestPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun newLocationData() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)

        fusedLocationProviderClient!!.requestLocationUpdates(
                locationRequest,locationCallback, Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            val number = "23224"
            val text = "whbdhubd"
            SmsManager.getDefault().sendTextMessage(number,null,text,null,null)
        }
    }

    //to check is location permissions are granted
    private fun checkPermission(): Boolean{
        if (ActivityCompat.checkSelfPermission(context!!, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(context!!, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            return true
        }
         return false
    }

    //to get user permission
    private fun requestPermission(){
        ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSION_ID
        )
    }


    //to check permission results
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == requestSendSms) {
            getLocation()
        }

        //used just for debugging
        if (requestCode == PERMISSION_ID){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug:", "Permission is granted")
            }
        }
    }
}