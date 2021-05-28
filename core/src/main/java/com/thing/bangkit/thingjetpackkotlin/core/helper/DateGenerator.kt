package com.thing.bangkit.thingjetpackkotlin.core.helper

import java.text.SimpleDateFormat
import java.util.*


object DateGenerator {
    fun modifyDateStringFormat(convertDate :String) : String {
        return try {
            val date = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).parse(convertDate)
            date?.let { SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(it) } ?: convertDate
        } catch (e: Exception) {
            convertDate
        }
    }
}