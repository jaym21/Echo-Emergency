package com.example.echoemergency.HelplinesActivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.echoemergency.databinding.ActivityHumanRightsHelplinesBinding

class HumanRightsHelplines : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityHumanRightsHelplinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHumanRightsHelplinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvHumanHelpline1.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1091)
            startActivity(dialIntent)
        }

        binding.cvHumanHelpline2.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 102)
            startActivity(dialIntent)
        }

        binding.cvHumanHelpline3.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1291)
            startActivity(dialIntent)
        }

        binding.cvHumanHelpline4.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1098)
            startActivity(dialIntent)
        }

    }
}