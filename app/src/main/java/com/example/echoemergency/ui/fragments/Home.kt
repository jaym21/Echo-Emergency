package com.example.echoemergency.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.echoemergency.ui.HelplinesActivity.AccidentHelplines
import com.example.echoemergency.ui.HelplinesActivity.HumanRightsHelplines
import com.example.echoemergency.ui.HelplinesActivity.MedicalHelplines
import com.example.echoemergency.ui.HelplinesActivity.PoliceHelplines
import com.example.echoemergency.databinding.FragmentHomeBinding

class Home : Fragment() {

    //viewBinding
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

            binding.cvPolice.setOnClickListener {
                context?.startActivity(Intent(context, PoliceHelplines::class.java))
            }

            binding.cvMedical.setOnClickListener {
                context?.startActivity(Intent(context, MedicalHelplines::class.java))
            }

            binding.cvAccident.setOnClickListener {
                context?.startActivity(Intent(context, AccidentHelplines::class.java))
            }

            binding.cvHumanRights.setOnClickListener {
                context?.startActivity(Intent(context, HumanRightsHelplines::class.java))
            }
    }

}