    package com.example.echoemergency.ui.fragments

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.echoemergency.MainActivity
import com.example.echoemergency.R
import com.example.echoemergency.adapters.INumberRVAdapter
import com.example.echoemergency.adapters.NumberRVAdapter
import com.example.echoemergency.components.NumberViewModel
import com.example.echoemergency.models.Number
import com.example.echoemergency.databinding.FragmentSettingsBinding
import java.util.*

    class Settings : Fragment(), INumberRVAdapter {

    //viewBinding
    private lateinit var binding: FragmentSettingsBinding

    private lateinit var viewModel: NumberViewModel
    private lateinit var langSelected: String

    //for alert shortcut notification
    val CHANNEL_NAME = "alert_shortcut"
    val CHANNERL_ID = "channel_alert"
    val NOTIFICATION_ID = 0

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

        createNotificationChannel()

        //loading Locale
        loadLocale()

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
//            adapter.updateList(it)
            adapter.setData(it)
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

        //changing app language according spinner selection when check button is clicked
        binding.btnChangeLang.setOnClickListener {

            langSelected = binding.spLanguage.selectedItem.toString()
            //changing language
            changeLang(langSelected)
        }


        //opening app on click on notification
        val notificationIntent = Intent(requireContext(), MainActivity::class.java)
        notificationIntent.putExtra("notificationFragment", "alertFragment")
        //making pending intent
        val pendingIntent = TaskStackBuilder.create(requireContext()).run {
            addNextIntentWithParentStack(notificationIntent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        //making alert shortcut notification
        val notificationAlert = NotificationCompat.Builder(requireContext(), CHANNERL_ID)
                .setContentText("Click to send alert in emergency")
                .setSmallIcon(R.drawable.ic_alert)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setOngoing(true)
                .setContentIntent(pendingIntent)
                .build()

        val notificationManager = NotificationManagerCompat.from(requireContext())

        //on switch flip
        binding.switchAlert.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                notificationManager.notify(NOTIFICATION_ID, notificationAlert)
            }
            else {
                notificationManager.cancel(NOTIFICATION_ID)
            }
        }

    }

    private fun changeLang(langSelected: String) {
        //setting Locale according to the language selected from the spinner
        if (langSelected == "English") {
            setLocale("en")
            recreate(requireActivity())
        }else if (langSelected == "Hindi") {
            setLocale("hi")
            recreate(requireActivity())
        }
    }

    //setting language selected as app language
    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)

        val config = Configuration()
        config.locale = locale
        requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)

        val editor = context?.getSharedPreferences("Settings", Context.MODE_PRIVATE)!!.edit()
        editor.putString("My_Lang", lang)
        editor.apply()

    }

    private fun loadLocale() {
        val sharedPreferences = context?.getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences!!.getString("My_Lang", "")
        setLocale(language!!)
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNERL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            val manager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onItemClicked(number: Number) {
        //whenever a number is clicked we call the delete fun in viewModel
        viewModel.deleteNumber(number)
    }


}