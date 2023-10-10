package com.example.mealsapp.di

import com.example.domain.repository.MealsRepositoryContract
import com.example.domain.usecase.GetMeals
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideUseCase(mealsRepositoryContract: MealsRepositoryContract) : GetMeals{
        return GetMeals(mealsRepositoryContract)
    }
}