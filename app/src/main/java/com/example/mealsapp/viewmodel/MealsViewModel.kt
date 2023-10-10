package com.example.mealsapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CategoryResponse
import com.example.domain.usecase.GetMeals
import com.example.mealsapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealsUseCase: GetMeals
) : ViewModel() {
    private val _categories = MutableLiveData<NetworkResult<CategoryResponse?>>()
    val categories = _categories

    fun getMeals(){
        viewModelScope.launch {
            _categories.postValue(NetworkResult.Loading())
        }
        viewModelScope.launch {
            try{
                _categories.postValue(NetworkResult.Success(getMealsUseCase()))

            } catch (e : Exception){
                Log.e("MealsViewModel", "getMeals ${e.localizedMessage}")
            }
        }
    }

}