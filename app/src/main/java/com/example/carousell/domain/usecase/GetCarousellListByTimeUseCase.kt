package com.example.carousell.domain.usecase

import com.example.carousell.common.Resource
import com.example.carousell.data.remote.dto.CarousellDto
import com.example.carousell.utils.ReadableTimeFormatUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCarousellListByTimeUseCase @Inject constructor() {

    operator fun invoke(carouselList: List<CarousellDto>): Flow<Resource<List<CarousellDto>>> =
        flow {

            try {
                emit(Resource.Loading())
                val carouselResponse = carouselList.sortedBy { it.time_created }.toMutableList()

                emit(
                    Resource.Success(
                        ReadableTimeFormatUtil.getListInReadableTimeFormat(
                            carouselResponse
                        )
                    )
                )

            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage!!))
            }
        }
}