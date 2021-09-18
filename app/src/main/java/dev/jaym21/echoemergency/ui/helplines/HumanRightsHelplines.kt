package dev.jaym21.echoemergency.ui.helplines

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dev.jaym21.echoemergency.databinding.ActivityHumanRightsHelplinesBinding

class HumanRightsHelplines : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityHumanRightsHelplinesBinding
    private val CALL_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHumanRightsHelplinesBinding.inflate(layoutInflater)
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


        binding.cvHumanHelpline1.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + 1091)
            startActivity(dialIntent)
        }

        binding.cvHumanHelpline2.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + 102)
            startActivity(dialIntent)
        }

        binding.cvHumanHelpline3.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + 1291)
            startActivity(dialIntent)
        }

        binding.cvHumanHelpline4.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_CALL)
            dialIntent.data = Uri.parse("tel:" + 1098)
            startActivity(dialIntent)
        }

    }
}