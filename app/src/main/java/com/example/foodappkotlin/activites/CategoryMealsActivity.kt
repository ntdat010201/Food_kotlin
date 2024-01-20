package com.example.foodappkotlin.activites

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodappkotlin.R
import com.example.foodappkotlin.VideoModel.CategoryMealsViewModel
import com.example.foodappkotlin.adapter.CategoryMealsAdapter
import com.example.foodappkotlin.databinding.ActivityCategoryMealsBinding
import com.example.foodappkotlin.fragment.HomeFragment
import com.example.foodappkotlin.utils.Const.Companion.CATEGORY_NAME

class CategoryMealsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCategoryMealsBinding

    private lateinit var categoryMealsViewModel: CategoryMealsViewModel
    private val categoryMealsAdapter by lazy { CategoryMealsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initData()
        initView()
        initListener()
    }

    private fun initData() {
        categoryMealsViewModel = ViewModelProviders.of(this)[CategoryMealsViewModel::class.java]
        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(CATEGORY_NAME)!!)
        categoryMealsViewModel.observeMealsLiveData().observe(this, Observer {mealsList ->
            binding.tvCategoryCount.text = mealsList.size.toString()
            categoryMealsAdapter.setMealsList(mealsList)
        })
    }

    private fun initView() {
        prepareRecyclerView()
    }

    private fun initListener() {

    }

    private fun prepareRecyclerView() {
        binding.rvMeals.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter= categoryMealsAdapter
        }
    }
}