package com.example.echoemergency

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.echoemergency.Helplines.AccidentHelplines
import com.example.echoemergency.Helplines.HumanRightsHelplines
import com.example.echoemergency.Helplines.MedicalHelplines
import com.example.echoemergency.Helplines.PoliceHelplines
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

            cvPolice.setOnClickListener {
                context?.startActivity(Intent(context, PoliceHelplines::class.java))
            }

            cvMedical.setOnClickListener {
                context?.startActivity(Intent(context, MedicalHelplines::class.java))
            }

            cvAccident.setOnClickListener {
                context?.startActivity(Intent(context, AccidentHelplines::class.java))
            }

            cvHumanRights.setOnClickListener {
                context?.startActivity(Intent(context, HumanRightsHelplines::class.java))
            }
    }

}