package com.example.foodappkotlin.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodappkotlin.databinding.FragmentMealBottomSheetBinding

private const val MEAL_ID = "param1"

class MealBottomSheetFragment : Fragment() {
    private var mealId: String? = null

    private lateinit var binding: FragmentMealBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealId = it.getString(MEAL_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        initData()
        initView()
        initListener()
        binding = FragmentMealBottomSheetBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    private fun initData() {
    }

    private fun initView() {
    }

    private fun initListener() {
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) = MealBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putString(MEAL_ID, param1)
            }
        }
    }
}