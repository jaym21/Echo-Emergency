package com.example.echoemergency.Helplines

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.echoemergency.R
import kotlinx.android.synthetic.main.activity_medical_helplines.*
import kotlinx.android.synthetic.main.activity_police_helplines.*

class MedicalHelplines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_helplines)

        cvMedicalHelpline1.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 102)
            startActivity(dialIntent)
        }

        cvMedicalHelpline2.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 9540161344)
            startActivity(dialIntent)
        }

        cvMedicalHelpline3.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1097)
            startActivity(dialIntent)
        }

        cvMedicalHelpline4.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1066)
            startActivity(dialIntent)
        }
    }
}