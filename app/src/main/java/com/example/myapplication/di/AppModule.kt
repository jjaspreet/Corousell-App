package com.example.myapplication.di

import com.example.myapplication.common.Constants
import com.example.myapplication.data.remote.CarousellApi
import com.example.myapplication.data.repository.CarousellRepositoryImpl
import com.example.myapplication.domain.repository.CarousellRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCarousellApi(): CarousellApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(CarousellApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCarousellRepository(api: CarousellApi): CarousellRepository {
        return CarousellRepositoryImpl(api)
    }

}