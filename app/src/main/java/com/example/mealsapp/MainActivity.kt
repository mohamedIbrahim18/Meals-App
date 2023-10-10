package com.example.mealsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mealsapp.adapter.MealsAdapter
import com.example.mealsapp.utils.NetworkResult
import com.example.mealsapp.viewmodel.MealsViewModel
import com.example.trymeals.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityMainBinding
    private val viewModel by viewModels<MealsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel.getMeals()
        val adapter = MealsAdapter()
        lifecycleScope.launch {
            viewModel.categories.observe(this@MainActivity){
                when(it){
                    is NetworkResult.Loading ->{
                        viewBinding.progressBar.visibility = View.VISIBLE
                    }
                    is NetworkResult.Success ->{
                        viewBinding.progressBar.visibility = View.GONE
                        adapter.differ.submitList(it.data?.categories)
                        viewBinding.categoryRv.adapter = adapter
                    }
                    is NetworkResult.Error-> {
                        viewBinding.progressBar.visibility = View.GONE
                        Log.e("MainActivity","OnCreate ${it.message.toString()}")
                    }

                    else -> {}
                }
            }
        }
    }
}