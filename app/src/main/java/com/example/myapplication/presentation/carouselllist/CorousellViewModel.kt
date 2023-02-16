package com.example.myapplication.presentation.carouselllist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.CarousellDto
import com.example.myapplication.domain.usecase.CarousellUseCase
import com.example.myapplication.domain.usecase.GetCarousellListByRankUseCase
import com.example.myapplication.domain.usecase.GetCarousellListByTimeUseCase
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

    private var _carousellResponse =
        MutableStateFlow<CarousellUIState>(CarousellUIState.Empty)
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
                   fetchResponseByTime(result.data!!)
                }
                is Resource.Error -> {
                    _carousellResponse.value = CarousellUIState.Error(
                        result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)

    }

    fun fetchResponseByRank(carouselList: List<CarousellDto>) {

        getCarouselListByRankUseCase(carouselList).onEach { result ->
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

        fun fetchResponseByTime(carouselList: List<CarousellDto>) {

            getCarouselListByTimeUseCase(carouselList).onEach { result ->
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

