package com.example.echoemergency.components

import androidx.lifecycle.LiveData
import com.example.echoemergency.database.Number
import com.example.echoemergency.database.NumberDAO

//Repository is basically a simple class which is layer used to provide a cleaner API to viewModel or UI to communicate with
class NumberRepository(private val numberDAO: NumberDAO) {

    //Observed LiveData will notify the observer when the data has changed.
    val allNumbers: LiveData<List<Number>> = numberDAO.getAllNumbers()  //this will get all people from PeopleDAO which will communicate with Database

    //to insert a person
    suspend fun insert(number: Number) = numberDAO.insert(number)

    //to delete a person
    suspend fun delete(number: Number) = numberDAO.delete(number)
}