package com.example.echoemergency.ui.helplines

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.echoemergency.databinding.ActivityAccidentHelplinesBinding

class AccidentHelplines : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityAccidentHelplinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccidentHelplinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvAccidentHelpline1.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 101)
            startActivity(dialIntent)
        }

        binding.cvAccidentHelpline2.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1073)
            startActivity(dialIntent)
        }

        binding.cvAccidentHelpline3.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1072)
            startActivity(dialIntent)
        }

        binding.cvAccidentHelpline4.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 9711077372)
            startActivity(dialIntent)
        }

        binding.cvAccidentHelpline5.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 108)
            startActivity(dialIntent)
        }
    }
}