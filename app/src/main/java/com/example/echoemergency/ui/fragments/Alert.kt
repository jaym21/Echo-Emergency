package com.example.echoemergency.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.*
import android.provider.Settings
import android.telephony.SmsManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.example.echoemergency.MainActivity
import com.example.echoemergency.components.NumberViewModel
import com.example.echoemergency.databinding.FragmentAlertBinding
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton

class Alert : Fragment() {

    //viewBinding
    private lateinit var binding: FragmentAlertBinding

    //it is just an int that must be unique so it can be any number
    private var PERMISSION_ID = 1000
    private var SMS_ID = 1001
    private var requestSendSms = 2

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var viewModel: NumberViewModel
    var longClickDuration : Long = 5000
    var isLongPress: Boolean = false



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

        //initializing viewModel by casting this as MainActivity so that we have access to the viewModel created in MainActivity
        viewModel = (activity as MainActivity).viewModel

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context as Activity)


        binding.btnAlert.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_DOWN) {
                    isLongPress = true
                    val handler = Handler()
                    handler.postDelayed(Runnable {
                        if(isLongPress) {
                           vibratePhone()
                            getLocation()
                        }
                    }, longClickDuration)
                } else if(event?.action == MotionEvent.ACTION_UP) {
                    isLongPress = false
                }
                return true
            }
        })

    }

    fun vibratePhone() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }

    //to get the last location
    private fun getLocation() {
        //first we need to check permission
        if (checkPermission()) {

            if (isLocationEnabled()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                    val location = it.result
                    if (location != null){
                        //location.latitude will give latitude and location.longitude will give longitude
                        //now for sending sms with longitude and latitude
                        if (ActivityCompat.checkSelfPermission(context as Activity, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                            sendSms(location)
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
            } else {
                enableGPS()
            }
        }else {
            requestPermission()
        }
    }

    private fun enableGPS() {
//        locationRequest = LocationRequest.create()
//                .setInterval(10*1000)
//                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//                .setFastestInterval(1*1000)
//        val settingBuilder = LocationSettingsRequest.Builder()
//                .addLocationRequest(locationRequest)
//                .setAlwaysShow(true)
//
//        val result: Task<LocationSettingsResponse> = LocationServices.getSettingsClient(context)
//                .checkLocationSettings(settingBuilder.build())
//
//        result.addOnCompleteListener {
//            try {
//                val response = it.getResult(ApiException::class)
//            }
//
//        }

        val gpsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(gpsIntent)

    }

    private fun sendSms(location: Location) {
        val smsManager = SmsManager.getDefault() as SmsManager

        viewModel.allNumbers.observe(viewLifecycleOwner, Observer { list -> list?.let {
            for (i in it) {
                smsManager.sendTextMessage(i.number, null, "http://maps.google.com?q=${location.latitude},${location.longitude}", null, null)
            }

        } })

        Toast.makeText(context, "Message sent successfully", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("MissingPermission")
    private fun newLocationData() {
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(0)
                .setFastestInterval(0)
                .setNumUpdates(1)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,locationCallback, Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            val lastLocation: Location = locationResult.lastLocation
            sendSms(lastLocation)
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

    //to check if location is enabled on device
    private fun isLocationEnabled(): Boolean{
        val locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
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