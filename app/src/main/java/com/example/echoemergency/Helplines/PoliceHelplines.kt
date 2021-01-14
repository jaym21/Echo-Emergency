package com.example.echoemergency.Helplines

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.echoemergency.R
import kotlinx.android.synthetic.main.activity_police_helplines.*

class PoliceHelplines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_police_helplines)

        cvPoliceHelpline1.setOnClickListener{
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:" + 100)
                startActivity(dialIntent)
        }

        cvPoliceHelpline2.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1094)
            startActivity(dialIntent)
        }

        cvPoliceHelpline3.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1363)
            startActivity(dialIntent)
        }

    }

}