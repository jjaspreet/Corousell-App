package com.example.carousell.domain.repository

import com.example.carousell.data.remote.dto.CarousellDto

interface CarousellRepository {
    suspend fun getCarousellData(): List<CarousellDto>
}