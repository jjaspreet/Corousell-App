package com.example.myapplication.presentation.carouselllist

import com.example.myapplication.data.remote.dto.CarousellDto

sealed class CarouselUIState {

    data class Success(val data: List<CarousellDto>): CarouselUIState()
    data class Error(val message: String) : CarouselUIState()
    object Loading : CarouselUIState()
    object Empty : CarouselUIState()
}