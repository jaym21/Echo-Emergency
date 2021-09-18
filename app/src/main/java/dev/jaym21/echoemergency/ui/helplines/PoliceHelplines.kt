package dev.jaym21.echoemergency.ui.helplines

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dev.jaym21.echoemergency.databinding.ActivityPoliceHelplinesBinding

class PoliceHelplines : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityPoliceHelplinesBinding
    private val CALL_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPoliceHelplinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //checking if permission is granted
        val permCall = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        //requesting if permission is not granted
        if (permCall == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                CALL_CODE
            )
        }

        binding.cvPoliceHelpline1.setOnClickListener{
                val dialIntent = Intent(Intent.ACTION_CALL)
                dialIntent.data = Uri.parse("tel:" + 100)
                startActivity(dialIntent)
        }

        binding.cvPoliceHelpline2.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + 1094)
            startActivity(dialIntent)
        }

        binding.cvPoliceHelpline3.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + 1363)
            startActivity(dialIntent)
        }

    }

}