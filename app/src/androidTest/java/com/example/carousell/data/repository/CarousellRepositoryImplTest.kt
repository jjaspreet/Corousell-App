package com.example.carousell.data.repository

import com.example.carousell.data.remote.dto.CarousellDto
import com.example.carousell.domain.repository.CarousellRepository
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CarousellRepositoryImplTest : CarousellRepository {

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    override suspend fun getCarousellData(): List<CarousellDto> {

        TODO("Not yet implemented")
    }

}