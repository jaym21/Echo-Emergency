package com.example.echoemergency.Helplines

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.echoemergency.R
import kotlinx.android.synthetic.main.activity_accident_helplines.*
import kotlinx.android.synthetic.main.activity_medical_helplines.*

class AccidentHelplines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accident_helplines)

        cvAccidentHelpline1.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 101)
            startActivity(dialIntent)
        }

        cvAccidentHelpline2.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1073)
            startActivity(dialIntent)
        }

        cvAccidentHelpline3.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1072)
            startActivity(dialIntent)
        }

        cvAccidentHelpline4.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 9711077372)
            startActivity(dialIntent)
        }

        cvAccidentHelpline5.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 108)
            startActivity(dialIntent)
        }
    }
}