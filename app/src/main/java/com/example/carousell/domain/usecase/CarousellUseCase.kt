package com.example.carousell.domain.usecase

import com.example.carousell.common.Resource
import com.example.carousell.data.remote.dto.CarousellDto
import com.example.carousell.domain.repository.CarousellRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CarousellUseCase @Inject constructor(
    private val repository: CarousellRepository
) {

    operator fun invoke(): Flow<Resource<List<CarousellDto>>> = flow {
        try {
            emit(Resource.Loading())
            val carouselResponse = repository.getCarousellData()
            emit(Resource.Success(carouselResponse))
        } catch (httpException: HttpException) {
            emit(Resource.Error(httpException.localizedMessage ?: "An unexpected error occurred"))
        } catch (ioException: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error("Couldn't reach server. Other exception."))
        }
    }
}