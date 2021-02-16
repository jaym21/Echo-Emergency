package com.example.echoemergency.components

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.echoemergency.database.Number
import com.example.echoemergency.database.NumberDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NumberViewModel(application: Application) : AndroidViewModel(application) {

    var allNumbers: LiveData<List<Number>>
    var repository: NumberRepository

    //initializing dao to get repository and allNumbers
    init {
        val dao = NumberDatabase.getDatabase(application).getNumberDAO()
        //instantiating repository which will communicate with dao to get allNumbers
        repository = NumberRepository(dao)
        //getting allNumbers
        allNumbers = repository.allNumbers
    }

    //calling insert function in repository, as it is a suspend fun in repository so we need to call it through viewModelScope which makes a background thread or coroutine
    fun insertNumber(number: Number) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(number)
    }

    //calling delete function in repository, as it is a suspend fun in repository so we need to call it through viewModelScope which makes a background thread or coroutine
    fun deleteNumber(number: Number) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(number)
    }
}