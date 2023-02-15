package com.example.myapplication.domain.usecase

import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.CarousellDto
import com.example.myapplication.domain.repository.CarouselRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CarouselUseCase @Inject constructor(
    private val repository: CarouselRepository
) {

    operator fun invoke(): Flow<Resource<List<CarousellDto>>> = flow {
        try {
            emit(Resource.Loading())
            delay(3000)
            val carouselResponse = repository.getCarouselData()
            emit(Resource.Success(carouselResponse))
        } catch (httpException: HttpException) {
            emit(Resource.Error(httpException.localizedMessage ?: "An unexpected error occurred"))
        } catch (ioException: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }catch (e : Exception){
            emit(Resource.Error("Couldn't reach server. Other exception."))
        }
    }
}