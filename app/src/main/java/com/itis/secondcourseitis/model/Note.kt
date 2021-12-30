package com.itis.secondcourseitis.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val desc: String,
    val date: Date,
    @ColumnInfo(name = "date_created") val dateCreated: Date,
    val longitude: Double,
    val latitude: Double
)
