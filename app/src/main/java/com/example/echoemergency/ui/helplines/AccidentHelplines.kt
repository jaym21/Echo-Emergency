package com.example.echoemergency.ui.helplines

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.echoemergency.databinding.ActivityAccidentHelplinesBinding

class AccidentHelplines : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityAccidentHelplinesBinding
    private val CALL_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccidentHelplinesBinding.inflate(layoutInflater)
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

        binding.cvAccidentHelpline1.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + 101)
            startActivity(dialIntent)
        }

        binding.cvAccidentHelpline2.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + 1073)
            startActivity(dialIntent)
        }

        binding.cvAccidentHelpline3.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + 1072)
            startActivity(dialIntent)
        }

        binding.cvAccidentHelpline4.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + 9711077372)
            startActivity(dialIntent)
        }

        binding.cvAccidentHelpline5.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + 108)
            startActivity(dialIntent)
        }
    }
}