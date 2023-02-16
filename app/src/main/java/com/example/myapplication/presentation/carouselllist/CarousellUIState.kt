package com.example.myapplication.presentation.carouselllist

import com.example.myapplication.data.remote.dto.CarousellDto

sealed class CarousellUIState {

    data class Success(val data: List<CarousellDto>): CarousellUIState()
    data class Error(val message: String) : CarousellUIState()
    object Loading : CarousellUIState()
    object Empty : CarousellUIState()
}