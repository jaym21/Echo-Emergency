package com.example.echoemergency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //creating fragments object
    lateinit var homeFragment: Home
    lateinit var alertFragment: Alert
    lateinit var profileFragment: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting default fragment as home
        homeFragment = Home()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        //setting bottom nav click listener
        bottomNavMenu.setOnNavigationItemSelectedListener {

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
                R.id.profile -> {
                    profileFragment = Profile()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, profileFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }

            true
        }
    }
}