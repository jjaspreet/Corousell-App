package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.dto.CarousellDto
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface CarouselRepository {

    suspend fun getCarouselData(): List<CarousellDto>
}