package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.CarousellApi
import com.example.myapplication.data.remote.dto.CarousellDto
import com.example.myapplication.domain.repository.CarousellRepository
import javax.inject.Inject

class CarousellRepositoryImpl @Inject constructor(
    private val carousellApi: CarousellApi
) : CarousellRepository {

    override suspend fun getCarousellData(): List<CarousellDto> {
        return carousellApi.fetchCarousellData()
    }
}