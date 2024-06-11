package com.example.foodappkotlin.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.foodappkotlin.activites.MainActivity
import com.example.foodappkotlin.activites.MealActivity
import com.example.foodappkotlin.databinding.FragmentMealBottomSheetBinding
import com.example.foodappkotlin.extension.showImgGlide
import com.example.foodappkotlin.utils.Const
import com.example.foodappkotlin.videoModel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private const val MEAL_ID = "param1"

class MealBottomSheetFragment : BottomSheetDialogFragment() {
    private var mealId: String? = null
    private lateinit var binding: FragmentMealBottomSheetBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealId = it.getString(MEAL_ID)
        }
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealBottomSheetBinding.inflate(LayoutInflater.from(requireContext()))
        initData()
        initView()
        initListener()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) = MealBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putString(MEAL_ID, param1)
            }
        }
    }

    private fun initData() {
    }

    private fun initView() {
        mealId?.let {
            viewModel.getMealById(it)
        }
        observeBottomSheetMeal()
    }

    private fun initListener() {
        onBottomSheetMealDialogClick()
    }

    private var mealName: String? = null
    private var mealThumb: String? = null
    private fun observeBottomSheetMeal() {
        viewModel.observeBottomSheetMeal().observe(viewLifecycleOwner, Observer { meal ->
            meal.strMealThumb?.let { showImgGlide(requireContext(), it, binding.imgBottomSheet) }
            binding.tvBottomLocation.text = meal.strArea
            binding.tvBottomSheetMealName.text = meal.strMeal
            binding.tvBottomSheet.text = meal.strTags

            mealName = meal.strMeal
            mealThumb = meal.strMealThumb
        })
    }

    private fun onBottomSheetMealDialogClick() {
        binding.bottomSheet.setOnClickListener {
            if (mealName != null && mealThumb != null) {
                val intent = Intent(activity, MealActivity::class.java)
                intent.apply {
                    putExtra(Const.MEAL_ID, mealId)
                    putExtra(Const.MEAL_NAME, mealName)
                    putExtra(Const.MEAL_THUMB, mealThumb)
                }
                startActivity(intent)
            }

        }
    }
}