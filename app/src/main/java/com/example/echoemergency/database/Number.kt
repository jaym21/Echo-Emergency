package com.example.echoemergency.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "numbers_table")
class Number (val number: String){
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
}