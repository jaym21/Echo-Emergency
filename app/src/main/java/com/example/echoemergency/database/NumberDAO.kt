package com.example.echoemergency.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NumberDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //if same number is being added it will be ignored by help of onConflict
    suspend fun insert(number: Number)

    @Delete
    suspend fun delete(number: Number)

    @Query("Select * from numbers_table order by id ASC") //getting all numbers by order of their id
    fun getAllNumbers(): LiveData<List<Number>> //LiveData is used to observe the changes made to data
}