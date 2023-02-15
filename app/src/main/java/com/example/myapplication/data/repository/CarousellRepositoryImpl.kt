package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.CarousellApi
import com.example.myapplication.data.remote.dto.CarousellDto
import com.example.myapplication.domain.repository.CarouselRepository
import javax.inject.Inject

class CarousellRepositoryImpl @Inject constructor(
    private val carousellApi: CarousellApi
) : CarouselRepository {

    override suspend fun getCarouselData(): List<CarousellDto> {
        return carousellApi.fetchCarousellData()
    }
}