package com.itis.secondcourseitis.recycler

import java.text.SimpleDateFormat
import java.util.*

object DateConverter {
    fun convertDate(date: Date): String {
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        return formatter.format(date)
    }
}
