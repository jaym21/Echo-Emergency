package dev.jaym21.echoemergency

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
import dev.jaym21.echoemergency.components.NumberViewModel
import dev.jaym21.echoemergency.ui.fragments.Alert
import dev.jaym21.echoemergency.databinding.ActivityMainBinding

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
                    .replace(_root_ide_package_.dev.jaym21.echoemergency.R.id.frameLayout, alertFragment)
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
        val navHostFragment = supportFragmentManager.findFragmentById(_root_ide_package_.dev.jaym21.echoemergency.R.id.navHostFragment)

        NavigationUI.setupWithNavController(binding!!.bottomNavView, navHostFragment!!.findNavController())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}