package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.dto.CarousellDto

interface CarousellRepository {

    suspend fun getCarousellData(): List<CarousellDto>
}