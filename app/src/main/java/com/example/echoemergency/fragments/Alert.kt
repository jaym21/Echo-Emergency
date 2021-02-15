package com.example.echoemergency.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.telephony.SmsManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.echoemergency.databinding.FragmentAlertBinding
import com.google.android.gms.location.*

class Alert : Fragment() {

    //viewBinding
    private lateinit var binding: FragmentAlertBinding

    //it is just an int that must be unique so it can be any number
    private var PERMISSION_ID = 1000
    private var SMS_ID = 1001
    private var requestSendSms = 2
    private val number = "9819601456"

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAlertBinding.inflate(layoutInflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context as Activity)

        binding.btnAlert.setOnClickListener {
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
                    //now for sending sms with longitude and latitude
                    if (ActivityCompat.checkSelfPermission(context as Activity, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        sendSms(number, location)
                    }else{
                        ActivityCompat.requestPermissions(
                                context as Activity,
                                arrayOf(android.Manifest.permission.SEND_SMS),SMS_ID
                        )
                    }
                } else{
                    //if the location is null, we will get new location of user
                    newLocationData()
                }
            }

        }else {
            requestPermission()
        }
    }

    private fun sendSms(number: String, location: Location) {
        val smsManager = SmsManager.getDefault() as SmsManager

        smsManager.sendTextMessage(number, null, "http://maps.google.com?q=${location.latitude},${location.longitude}", null, null)
    }

    @SuppressLint("MissingPermission")
    private fun newLocationData() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        fusedLocationProviderClient!!.requestLocationUpdates(
                locationRequest,locationCallback, Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            sendSms(number, lastLocation)
        }
    }

    //to check is location permissions are granted
    private fun checkPermission(): Boolean{
        if (ActivityCompat.checkSelfPermission(context as Activity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(context as Activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context as Activity, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
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
        ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.SEND_SMS),SMS_ID
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