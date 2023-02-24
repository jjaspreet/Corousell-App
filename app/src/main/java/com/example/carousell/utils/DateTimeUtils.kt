package com.example.carousell.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    fun getDateForRespectiveMilliseconds(milliseconds: Long): String {
        val date = Date()
        date.time = milliseconds
        return SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(date)
    }

    fun findDifference(
        start_date: String?, end_date: String?
    ): String {

        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())

        try {

            val d1 = sdf.parse(start_date!!)
            val d2 = sdf.parse(end_date!!)

            var differenceInTime = 0L
            d2?.let { d2 ->
                d1?.let { d1 ->
                    differenceInTime = d2.time - d1.time
                }
            }

            return getDifferenceInRespectiveFormat(differenceInTime)

        }
        catch (e: ParseException) {
            e.printStackTrace()
        }

        //Default return type
        return ""
    }

    private fun getDifferenceInRespectiveFormat(differenceInTime: Long): String {

        val differenceInSeconds = ((differenceInTime / 1000) % 60)
        val differenceInMinutes = ((differenceInTime / (1000 * 60)) % 60)
        val differenceInHours = ((differenceInTime / (1000 * 60 * 60)) % 24)
        val differenceInYears = (differenceInTime / (1000L * 60 * 60 * 24 * 365))
        val differenceInMonths = (differenceInTime / (1000L * 60 * 60 * 24 * 30))
        val differenceInDays = ((differenceInTime / (1000 * 60 * 60 * 24)) % 365)

        // Return the difference in time
        return if (differenceInYears > 1) {
            return "$differenceInYears years ago"
        } else if (differenceInMonths > 1) {
            return "$differenceInMonths months ago"
        } else if (differenceInDays > 1) {
            return "$differenceInDays days ago"
        } else if (differenceInHours < 24) {
            return "$differenceInHours hours ago"
        } else if (differenceInMinutes < 60) {
            return "$differenceInMinutes minutes  ago"
        } else if (differenceInSeconds < 60) {
            return "$differenceInSeconds seconds ago"
        } else {
            return "$differenceInDays days ago"
        }
    }

    fun getCurrentDateAndTime(): String {
        return SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())
    }
}