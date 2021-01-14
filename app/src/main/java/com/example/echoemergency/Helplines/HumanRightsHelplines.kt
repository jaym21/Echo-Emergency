package com.example.echoemergency.Helplines

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.echoemergency.R
import kotlinx.android.synthetic.main.activity_accident_helplines.*
import kotlinx.android.synthetic.main.activity_human_rights_helplines.*

class HumanRightsHelplines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_human_rights_helplines)

        cvHumanHelpline1.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1091)
            startActivity(dialIntent)
        }

        cvHumanHelpline2.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 102)
            startActivity(dialIntent)
        }

        cvHumanHelpline3.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1291)
            startActivity(dialIntent)
        }

        cvHumanHelpline4.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + 1098)
            startActivity(dialIntent)
        }

    }
}