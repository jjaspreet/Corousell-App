package com.example.myapplication.presentation.carouselllist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.CarousellDto
import com.example.myapplication.domain.usecase.CarouselUseCase
import com.example.myapplication.domain.usecase.GetCarouselListByRankUseCase
import com.example.myapplication.domain.usecase.GetCarouselListByTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CorouselViewModel @Inject constructor(
    private val useCase: CarouselUseCase,
    private val getCarouselListByRankUseCase: GetCarouselListByRankUseCase,
    private val getCarouselListByTimeUseCase: GetCarouselListByTimeUseCase
) : ViewModel() {

    private var _carousellResponse =
        MutableStateFlow<CarouselUIState>(CarouselUIState.Empty)
    val carouselResponse: StateFlow<CarouselUIState> = _carousellResponse

    init {
        fetchResponse()
    }

    private fun fetchResponse() {

        useCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _carousellResponse.value = CarouselUIState.Loading
                }
                is Resource.Success -> {
                    _carousellResponse.value = CarouselUIState.Success(result.data!!)
                }
                is Resource.Error -> {
                    _carousellResponse.value = CarouselUIState.Error(
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
                    _carousellResponse.value = CarouselUIState.Loading
                }
                is Resource.Success -> {
                    _carousellResponse.value = CarouselUIState.Success(result.data!!)
                }
                is Resource.Error -> {
                    _carousellResponse.value = CarouselUIState.Error(
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
                        _carousellResponse.value = CarouselUIState.Loading
                    }
                    is Resource.Success -> {
                        _carousellResponse.value = CarouselUIState.Success(result.data!!)
                    }
                    is Resource.Error -> {
                        _carousellResponse.value = CarouselUIState.Error(
                            result.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }.launchIn(viewModelScope)

        }
    }

