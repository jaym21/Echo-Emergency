package com.example.echoemergency

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.echoemergency.ui.OnBoarding

class SplashActivity: AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (restorePrefData()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else {
            val intent = Intent(this, OnBoarding::class.java)
            startActivity(intent)
        }

    }

    private fun restorePrefData(): Boolean{
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isFirstTimeRun", false)
    }
}