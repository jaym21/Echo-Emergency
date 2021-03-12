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
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.echoemergency.components.NumberViewModel
import com.example.echoemergency.databinding.ActivityMainBinding
import com.example.echoemergency.ui.fragments.Alert
import com.example.echoemergency.ui.fragments.Home
import com.example.echoemergency.ui.fragments.Settings

class MainActivity : AppCompatActivity() {

    //viewBinding
    private var binding: ActivityMainBinding? = null

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
        setContentView(binding?.root)

//        createNotificationChannel()

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

    private fun setUpBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)

        NavigationUI.setupWithNavController(binding!!.bottomNavView, navHostFragment!!.findNavController())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
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