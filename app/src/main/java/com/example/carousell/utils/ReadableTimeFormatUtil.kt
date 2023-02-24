package com.example.carousell.utils

import com.example.carousell.data.remote.dto.CarousellDto

object ReadableTimeFormatUtil {

    fun getListInReadableTimeFormat(carouselList: List<CarousellDto>): List<CarousellDto> {

        val filteredList = arrayListOf<CarousellDto>()
        carouselList.map { item ->
            val time = DateTimeUtils.getDateForRespectiveMilliseconds((item.time_created).toLong())
            item.currentTime =
                DateTimeUtils.findDifference(time, DateTimeUtils.getCurrentDateAndTime())
            filteredList.add(item)
        }.toList()

        return filteredList
    }
}