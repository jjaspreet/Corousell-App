package com.example.carousell.utils

import com.example.carousell.data.remote.dto.CarousellDto
import java.util.concurrent.TimeUnit

object DateTimeUtils {

    private fun getTimeDifference(milliseconds: Long): String {

        // long minutes = (milliseconds / 1000) / 60;
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)

        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)

        val days = TimeUnit.MILLISECONDS.toDays(milliseconds)

        val time = if (days > 1) {
            return "$days days ago"
        } else if (hours > 1) {
            return "$hours hours ago"
        } else if (minutes > 1) {
            return "$minutes minute ago"
        } else {
            ""
        }

        return time
    }

    fun getCurrentTime(carouselList: List<CarousellDto>): List<CarousellDto> {
        val filteredList = ArrayList<CarousellDto>()

        carouselList.map {
            it.currentTime = getTimeDifference((it.time_created).toLong())
            filteredList.add(it)
        }.toList()

        return filteredList
    }
}