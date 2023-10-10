package com.example.domain.repository

import com.example.domain.entity.CategoryResponse

interface MealsRepositoryContract {

    suspend fun getMealsFromRemote() : CategoryResponse
}