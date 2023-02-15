package com.example.myapplication.domain.usecase

import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.CarousellDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCarouselListByRankUseCase @Inject constructor(
) {

    operator fun invoke (carouselList: List<CarousellDto>): Flow<Resource<List<CarousellDto>>> = flow {

        try {
            emit(Resource.Loading())
            delay(3000)
            val rickyMortyResponse = carouselList.sortedBy { it.rank }.toMutableList()
            emit(Resource.Success(rickyMortyResponse))
        } catch (e : Exception){
            emit(Resource.Error("Couldn't reach server. Other exception."))
        }
    }
}