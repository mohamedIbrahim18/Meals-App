package com.example.domain.usecase

import com.example.domain.repository.MealsRepositoryContract

class GetMeals (private val mealsRepo: MealsRepositoryContract) {
    suspend operator fun invoke() = mealsRepo.getMealsFromRemote()
}