package com.example.echoemergency.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.echoemergency.MainActivity
import com.example.echoemergency.adapters.INumberRVAdapter
import com.example.echoemergency.adapters.NumberRVAdapter
import com.example.echoemergency.components.NumberViewModel
import com.example.echoemergency.database.Number
import com.example.echoemergency.databinding.FragmentSettingsBinding

class Settings : Fragment(), INumberRVAdapter {

    //viewBinding
    private lateinit var binding: FragmentSettingsBinding

    private lateinit var viewModel: NumberViewModel
    private lateinit var spinner: Spinner
    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //initializing spinner
        spinner = binding.spLanguage

        arrayAdapter = ArrayAdapter(this, )

        //initializing viewModel by casting this as MainActivity so that we have access to the viewModel created in MainActivity
        viewModel = (activity as MainActivity).viewModel

        //initializing recyclerView
        binding.rvAlertNumber.layoutManager = LinearLayoutManager(activity)

        //initializing adapter
        val adapter = NumberRVAdapter(requireContext(), this)

        //passing adapter to recyclerView
        binding.rvAlertNumber.adapter = adapter

        //observing allNumbers LiveData to see changes and update ui if changes are made
        viewModel.allNumbers.observe(viewLifecycleOwner, Observer { list -> list?.let {
            //checking if the list is null that is no change is observed then this function won't get executed
            adapter.updateList(it)
        } })


        binding.btnSave.setOnClickListener {

            val enteredNumber = binding.etNumber.text.toString()

            //checking number is empty or not before adding to the list
            if (enteredNumber.isNotEmpty()) {
                viewModel.insertNumber(Number(enteredNumber))
            } else {
                Toast.makeText(context, "Enter number of person to be saved", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onItemClicked(number: Number) {
        //whenever a number is clicked we call the delete fun in viewModel
        viewModel.deleteNumber(number)
    }


}