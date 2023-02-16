package com.example.myapplication.domain.usecase

import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.CarousellDto
import com.example.myapplication.utils.DateTimeUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCarouselListByTimeUseCase @Inject constructor()
{

    operator fun invoke (carouselList: List<CarousellDto>): Flow<Resource<List<CarousellDto>>> = flow {

        try {
            emit(Resource.Loading())
            val carouselResponse = carouselList.sortedBy { it.time_created }.toMutableList()

            emit(Resource.Success(DateTimeUtils.getCurrentTime(carouselResponse)))

        } catch (e : Exception){
            emit(Resource.Error("Couldn't reach server. Other exception."))
        }
    }
}