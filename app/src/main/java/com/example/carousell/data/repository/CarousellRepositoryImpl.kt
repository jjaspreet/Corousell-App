package com.example.carousell.data.repository

import com.example.carousell.data.remote.CarousellApi
import com.example.carousell.data.remote.dto.CarousellDto
import com.example.carousell.domain.repository.CarousellRepository
import javax.inject.Inject

class CarousellRepositoryImpl @Inject constructor(
    private val carousellApi: CarousellApi
) : CarousellRepository {

    override suspend fun getCarousellData(): List<CarousellDto> {
        return carousellApi.fetchCarousellData()
    }
}