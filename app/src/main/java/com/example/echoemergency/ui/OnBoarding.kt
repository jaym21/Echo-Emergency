package com.example.echoemergency.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.echoemergency.MainActivity
import com.example.echoemergency.R
import com.example.echoemergency.adapters.OnBoardingViewPagerAdapter
import com.example.echoemergency.databinding.ActivityOnBoardingBinding
import com.example.echoemergency.models.OnBoardingData
import com.google.android.material.tabs.TabLayout

class OnBoarding : AppCompatActivity() {

    private var binding: ActivityOnBoardingBinding? = null
    private var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    lateinit var tabLayout: TabLayout
    private var onBoardingViewPager: ViewPager? = null
    lateinit var next: Button
    var position = 0
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (restorePrefData()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        tabLayout = binding?.tabIndicator!!
        next = binding?.btnNext!!

        //making OnBoardingData list
        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Call emergency helplines", "You can click the number and immediately", R.drawable.ic_ambulance))
        onBoardingData.add(OnBoardingData("Alert your numbers", "You can alert the numbers saved in the settings by pressing the alert button for more than 5 seconds", R.drawable.ic_alert))
        onBoardingData.add(OnBoardingData("Change the Language", "The app language can be changed by choosing it from settings", R.drawable.ic_settings))
        onBoardingData.add(OnBoardingData("Shortcut for Alert", "You can add an alert shortcut in notifications of your phone, on click which will open the alert button", R.drawable.ic_alert))

        setOnBoardingViewPagerAdapter(onBoardingData)

        position = onBoardingViewPager!!.currentItem

        next.setOnClickListener {

            if (position < onBoardingData.size) {
                position++
                onBoardingViewPager!!.currentItem = position
            }

            if (position == onBoardingData.size) {
                savePrefData()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position
                //if we are at second last intro screen tgen changing next button into finish for last screen
                if (tab.position == (onBoardingData.size - 1 )) {
                    next.text = "Finish"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>) {
        onBoardingViewPager = findViewById(R.id.screenPager)
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoardingViewPager!!.adapter = onBoardingViewPagerAdapter
        tabLayout.setupWithViewPager(onBoardingViewPager)
    }


    private fun savePrefData() {
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    private fun restorePrefData(): Boolean{
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isFirstTimeRun", false)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}