package com.example.echoemergency

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.echoemergency.components.NumberViewModel
import com.example.echoemergency.databinding.ActivityMainBinding
import com.example.echoemergency.ui.fragments.Alert

class MainActivity : AppCompatActivity() {

    //viewBinding
    private var binding: ActivityMainBinding? = null

    //creating fragments object
    lateinit var alertFragment: Alert
    lateinit var viewModel: NumberViewModel
    val CALL_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        //settings up bottom navigation
        setUpBottomNavigation()

        //when notification is clicked
        val menuFragment = intent.getStringExtra("notificationFragment")
        if (menuFragment != null) {
            alertFragment = Alert()
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, alertFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
        }

        //creating a instance or object of viewModel
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NumberViewModel::class.java)


        //asking for CALL_PHONE permission
        val permCall = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)

        if (permCall == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                CALL_CODE
            )
        }
    }

    private fun setUpBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)

        NavigationUI.setupWithNavController(binding!!.bottomNavView, navHostFragment!!.findNavController())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}