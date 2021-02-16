package com.example.echoemergency.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [People::class], version = 1, exportSchema = false)
abstract class PeopleDatabase: RoomDatabase() {

    abstract fun getPeopleDAO(): PeopleDAO

    companion object {
        //Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: PeopleDatabase? = null

        fun getDatabase(context: Context): PeopleDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE?: synchronized(this) {   //synchronized lock is put here so that two threads can not access database at once
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PeopleDatabase::class.java,
                    "people_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}