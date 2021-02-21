package com.example.echoemergency

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.echoemergency.components.NumberViewModel
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
    lateinit var viewModel: NumberViewModel

      //for alert shortcut notification
//    val CHANNEL_NAME = "alert_shortcut"
//    val CHANNERL_ID = "channel_alert"
//    val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        createNotificationChannel()

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

        //creating a instance or object of viewModel
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NumberViewModel::class.java)


//        //making alert shortcut notification
//        val notificationAlert = NotificationCompat.Builder(this, CHANNERL_ID)
//                .setContentTitle("Echo Emergency")
//                .setContentText("Click to send alert in emergency")
//                .setSmallIcon(R.drawable.ic_alert)
//                .setPriority(NotificationCompat.PRIORITY_MAX)
//                .build()
//
//        val notificationManager = NotificationManagerCompat.from(this)
//
//        //initializing MyApplication
//        var mApp = MyApplication()
//        //getting global variable value
//        var sendAlertNotification = mApp.sendAlertNotification
//        if(sendAlertNotification == 1) {
//            notificationManager.notify(NOTIFICATION_ID, notificationAlert)
//        }

    }
//
//    fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(CHANNERL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
//            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            manager.createNotificationChannel(channel)
//        }
//    }
}