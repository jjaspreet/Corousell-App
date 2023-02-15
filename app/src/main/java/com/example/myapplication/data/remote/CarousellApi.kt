package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.dto.CarousellDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CarousellApi {

    @GET("carousell-interview-assets/android/carousell_news.json")
    suspend fun fetchCarousellData(): List<CarousellDto>

}