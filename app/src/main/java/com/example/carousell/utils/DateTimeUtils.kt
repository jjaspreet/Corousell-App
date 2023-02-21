package com.example.carousell.utils

import com.example.carousell.data.remote.dto.CarousellDto
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    private fun getDateForRespectiveMilliseconds(milliseconds: Long): String {
        val date = Date()
        date.time = milliseconds
        return SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(date)
    }

    // Function to print difference in
    // time start_date and end_date
    private fun findDifference(
        start_date: String?, end_date: String?
    ): String {

        // SimpleDateFormat converts the
        // string format to date object
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())

        // Try Block
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date

            val d1 = sdf.parse(start_date!!)
            val d2 = sdf.parse(end_date!!)

            // Calculate time difference
            // in milliseconds

            var differenceInTime = 0L
            d2?.let { d2 ->
                d1?.let { d1 ->
                    differenceInTime = d2.time - d1.time
                }
            }

            // Calculate time difference in
            // seconds, minutes, hours, years,
            // and days
            return getDifferenceInRespectiveFormat(differenceInTime)

        } // Catch the Exception
        catch (e: ParseException) {
            e.printStackTrace()
        }

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

    private fun getCurrentDateAndTime(): String{

        return SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())
    }

    fun getListInReadableTimeFormat(carouselList: List<CarousellDto>): List<CarousellDto> {

        val filteredList = ArrayList<CarousellDto>()
        carouselList.map { item ->
            val time = getDateForRespectiveMilliseconds((item.time_created).toLong())
            item.currentTime = findDifference(time, getCurrentDateAndTime())
            filteredList.add(item)
        }.toList()

        return filteredList
    }
}