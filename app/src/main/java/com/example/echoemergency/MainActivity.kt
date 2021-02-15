package com.example.echoemergency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.echoemergency.databinding.ActivityMainBinding
import com.example.echoemergency.fragments.Alert
import com.example.echoemergency.fragments.Home
import com.example.echoemergency.fragments.Settings

class MainActivity : AppCompatActivity() {

    //viewBinding
    lateinit var binding: ActivityMainBinding

    //creating fragments object
    lateinit var homeFragment: Home
    lateinit var alertFragment: Alert
    lateinit var settingsFragment: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting default fragment as home
        homeFragment = Home()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        //setting bottom nav click listener
        binding.bottomNavMenu.setOnNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.home -> {
                    homeFragment = Home()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.alert -> {
                    alertFragment = Alert()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, alertFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.settings -> {
                    settingsFragment = Settings()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, settingsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }

            true
        }
    }
}