package com.example.carousell.presentation.carouselllist

import com.example.carousell.data.remote.dto.CarousellDto

sealed class CarousellUIState {

    data class Success(val data: List<CarousellDto>): CarousellUIState()
    data class Error(val message: String) : CarousellUIState()
    object Loading : CarousellUIState()
    object Empty : CarousellUIState()
}