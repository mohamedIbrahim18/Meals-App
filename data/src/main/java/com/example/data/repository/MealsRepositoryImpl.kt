package com.example.data.repository

import com.example.data.remote.WebService
import com.example.domain.entity.CategoryResponse
import com.example.domain.repository.MealsRepositoryContract

class MealsRepositoryImpl (private val apiService: WebService) : MealsRepositoryContract{
    override suspend fun getMealsFromRemote(): CategoryResponse = apiService.getMeals()

}