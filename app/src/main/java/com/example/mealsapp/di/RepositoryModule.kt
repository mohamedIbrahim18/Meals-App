package com.example.mealsapp.di

import com.example.data.remote.WebService
import com.example.data.repository.MealsRepositoryImpl
import com.example.domain.repository.MealsRepositoryContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideRepo(webService: WebService) : MealsRepositoryContract{
        return MealsRepositoryImpl(webService)
    }
}