package com.example.echoemergency.HelplinesActivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.echoemergency.databinding.ActivityPoliceHelplinesBinding

class PoliceHelplines : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityPoliceHelplinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPoliceHelplinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvPoliceHelpline1.setOnClickListener{
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:" + 100)
                startActivity(dialIntent)
        }

        binding.cvPoliceHelpline2.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1094)
            startActivity(dialIntent)
        }

        binding.cvPoliceHelpline3.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1363)
            startActivity(dialIntent)
        }

    }

}