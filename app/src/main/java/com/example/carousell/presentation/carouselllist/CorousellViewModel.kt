package com.example.carousell.presentation.carouselllist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carousell.common.Resource
import com.example.carousell.data.remote.dto.CarousellDto
import com.example.carousell.domain.usecase.CarousellUseCase
import com.example.carousell.domain.usecase.GetCarousellListByRankUseCase
import com.example.carousell.domain.usecase.GetCarousellListByTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CorousellViewModel @Inject constructor(
    private val useCase: CarousellUseCase,
    private val getCarouselListByRankUseCase: GetCarousellListByRankUseCase,
    private val getCarouselListByTimeUseCase: GetCarousellListByTimeUseCase
) : ViewModel() {

    private lateinit var carousellResponse: List<CarousellDto>

    private var _carousellResponse = MutableStateFlow<CarousellUIState>(CarousellUIState.Empty)
    val carouselResponse: StateFlow<CarousellUIState> = _carousellResponse

    init {
        fetchResponse()
    }

    private fun fetchResponse() {

        useCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _carousellResponse.value = CarousellUIState.Loading
                }
                is Resource.Success -> {
                    carousellResponse = result.data!!
                    fetchResponseByTime()

                }
                is Resource.Error -> {
                    _carousellResponse.value = CarousellUIState.Error(
                        result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun fetchResponseByRank() {

        getCarouselListByRankUseCase(carousellResponse).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _carousellResponse.value = CarousellUIState.Loading
                }
                is Resource.Success -> {
                    _carousellResponse.value = CarousellUIState.Success(result.data!!)
                }
                is Resource.Error -> {
                    _carousellResponse.value = CarousellUIState.Error(
                        result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun fetchResponseByTime() {

        getCarouselListByTimeUseCase(carousellResponse).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _carousellResponse.value = CarousellUIState.Loading
                }
                is Resource.Success -> {
                    _carousellResponse.value = CarousellUIState.Success(result.data!!)
                }
                is Resource.Error -> {
                    _carousellResponse.value = CarousellUIState.Error(
                        result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}

