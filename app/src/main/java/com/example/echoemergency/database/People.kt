package com.example.echoemergency.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people_table")
class People (
        val name: String,
        val number: String,
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
)