package com.example.carousell.data.remote

import com.example.carousell.data.remote.dto.CarousellDto
import retrofit2.http.GET

interface CarousellApi {

    @GET("carousell-interview-assets/android/carousell_news.json")
    suspend fun fetchCarousellData(): List<CarousellDto>

}