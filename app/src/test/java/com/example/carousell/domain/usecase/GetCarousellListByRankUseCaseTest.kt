package com.example.carousell.domain.usecase

import app.cash.turbine.test
import com.example.carousell.data.remote.dto.CarousellDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCarousellListByRankUseCaseTest {

    lateinit var getCarousellListByRankUseCase: GetCarousellListByRankUseCase
    val carousellList = arrayListOf<CarousellDto>()


    @Before
    fun setUp() {
        getCarousellListByRankUseCase = GetCarousellListByRankUseCase()
    }


    @Test
    fun `sort the carousel list by rank`() = runTest {

        getCarousellListByRankUseCase(setTheCarousellListForTest()).test {
            val emission = awaitItem()

        }

    }

    private fun setTheCarousellListForTest(): List<CarousellDto> {

        carousellList.add(CarousellDto("", "", "", 1, 0, "", ""))
        carousellList.add(CarousellDto("", "", "", 3, 0, "", ""))
        carousellList.add(CarousellDto("", "", "", 5, 0, "", ""))
        carousellList.add(CarousellDto("", "", "", 0, 0, "", ""))
        carousellList.add(CarousellDto("", "", "", 4, 0, "", ""))
        carousellList.add(CarousellDto("", "", "", 2, 0, "", ""))

        return carousellList
    }

}