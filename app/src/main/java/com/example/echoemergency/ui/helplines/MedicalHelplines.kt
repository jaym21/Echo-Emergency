package com.example.echoemergency.ui.helplines

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.echoemergency.databinding.ActivityMedicalHelplinesBinding

class MedicalHelplines : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityMedicalHelplinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicalHelplinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvMedicalHelpline1.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 102)
            startActivity(dialIntent)
        }

        binding.cvMedicalHelpline2.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 9540161344)
            startActivity(dialIntent)
        }

        binding.cvMedicalHelpline3.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1097)
            startActivity(dialIntent)
        }

        binding.cvMedicalHelpline4.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1066)
            startActivity(dialIntent)
        }
    }
}