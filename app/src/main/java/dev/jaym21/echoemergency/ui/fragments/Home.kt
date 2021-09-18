package dev.jaym21.echoemergency.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.jaym21.echoemergency.ui.helplines.AccidentHelplines
import dev.jaym21.echoemergency.ui.helplines.HumanRightsHelplines
import dev.jaym21.echoemergency.ui.helplines.MedicalHelplines
import dev.jaym21.echoemergency.ui.helplines.PoliceHelplines
import dev.jaym21.echoemergency.databinding.FragmentHomeBinding

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