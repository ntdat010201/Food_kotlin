package com.example.foodappkotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodappkotlin.videoModel.HomeViewModel
import com.example.foodappkotlin.activites.MainActivity
import com.example.foodappkotlin.activites.MealActivity
import com.example.foodappkotlin.adapter.CategoriesAdapter
import com.example.foodappkotlin.adapter.MostPopularAdapter
import com.example.foodappkotlin.databinding.FragmentHomeBinding
import com.example.foodappkotlin.dialog.MealBottomSheetFragment
import com.example.foodappkotlin.extension.showImgGlide
import com.example.foodappkotlin.pojo.Meal
import com.example.foodappkotlin.pojo.MealsByCategory
import com.example.foodappkotlin.utils.Const.Companion.MEAL_ID
import com.example.foodappkotlin.utils.Const.Companion.MEAL_NAME
import com.example.foodappkotlin.utils.Const.Companion.MEAL_THUMB

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularItemsAdapter: MostPopularAdapter
    private lateinit var categoriesAdapter : CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentHomeBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        initData()
        initView()
        initListener()
        return binding.root
    }

    private fun initData() {
//        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]
        viewModel = (activity as MainActivity).viewModel
        popularItemsAdapter = MostPopularAdapter()
        categoriesAdapter = CategoriesAdapter()
    }

    private fun initView() {
        viewModel.getRandomMeal()
        observerRandomMeal()

        preparePopularItemsRecyclerView()

        viewModel.getPopularItems()
        observerPopularItemsLivData()

        viewModel.getCategories()
        observerCategoryLiveData()

        prepareCategoriesRecyclerView()

    }

    private fun initListener() {
        onRandomMealClick()
        onPopularItemClick()
        onCategoryClick()
        onPopularItemLongClick()
    }

    private fun onPopularItemLongClick() {
        popularItemsAdapter.onLongItemClick = { meal ->
            val mealBottomSheetFragment = MealBottomSheetFragment.newInstance(meal.idMeal)
            mealBottomSheetFragment.show(childFragmentManager, "Meal Info")

        }
    }

    private fun onCategoryClick() {
//        categoriesAdapter.onItemClick = { category ->
//            val intent = Intent(requireContext(), CategoryMealsActivity::class.java)
//            intent.putExtra(CATEGORY_NAME, category.strCategory)
//            startActivity(intent)
//
//        }
    }

    private fun prepareCategoriesRecyclerView() {
        binding.recViewCategories.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

    private fun observerCategoryLiveData() {
        viewModel.observerCategoriesLiveData().observe(viewLifecycleOwner, Observer { categories ->
            categoriesAdapter.setCategoryList(categories)
        })
    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick = { meal ->
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_NAME, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerPopularItemsLivData() {
        viewModel.observerPopularItemsLiveData().observe(
            viewLifecycleOwner
        ) { mealList ->
            popularItemsAdapter.setMeals(mealsList = mealList as ArrayList<MealsByCategory>)
        }
    }

    private fun preparePopularItemsRecyclerView() {
        binding.recViewMealsPopular.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularItemsAdapter
        }
    }

    private fun onRandomMealClick() {
        binding.randomMealCard.setOnClickListener {
            val intent = Intent(requireContext(), MealActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        viewModel.observerRandomMealLivedata().observe(viewLifecycleOwner) { meal ->
            showImgGlide(requireContext(), meal.strMealThumb!!, binding.imgRandomMeal)
            this.randomMeal = meal
        }
    }

}