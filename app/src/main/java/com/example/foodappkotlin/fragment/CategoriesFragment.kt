package com.example.foodappkotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodappkotlin.R
import com.example.foodappkotlin.VideoModel.HomeViewModel
import com.example.foodappkotlin.activites.MainActivity
import com.example.foodappkotlin.adapter.CategoriesAdapter
import com.example.foodappkotlin.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding

    private val categoriesAdapter by lazy { CategoriesAdapter() }
    private lateinit var viewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(LayoutInflater.from(requireContext()))
        initData()
        initView()
        initListener()
        return binding.root
    }

    private fun initData() {
        viewModel = (activity as MainActivity).viewModel
    }

    private fun initView() {
        prepareRecyclerView()
        observeCategories()
    }


    private fun initListener() {

    }

    private fun observeCategories() {
        viewModel.observerCategoriesLiveData().observe(viewLifecycleOwner, Observer { categories ->
            categoriesAdapter.setCategoryList(categories)
        })
    }

    private fun prepareRecyclerView() {
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }
    }

}