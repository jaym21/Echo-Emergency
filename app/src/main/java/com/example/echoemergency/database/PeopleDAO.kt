package com.example.echoemergency.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PeopleDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //if same person is being added it will be ignored by help of onConflict
    suspend fun insert(people: People)

    @Delete
    suspend fun delete(people: People)

    @Query("Select * from people_table order by id ASC") //getting all people by order of their id
    fun getAllPeople(): LiveData<List<People>> //LiveData is used to observe the changes made to data
}