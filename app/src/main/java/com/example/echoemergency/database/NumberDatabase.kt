package com.example.echoemergency.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.echoemergency.models.Number

@Database(entities = [Number::class], version = 1, exportSchema = false)
abstract class NumberDatabase: RoomDatabase() {

    abstract fun getNumberDAO(): NumberDAO

    companion object {
        //Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: NumberDatabase? = null

        fun getDatabase(context: Context): NumberDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE?: synchronized(this) {   //synchronized lock is put here so that two threads can not access database at once
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NumberDatabase::class.java,
                    "number_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}