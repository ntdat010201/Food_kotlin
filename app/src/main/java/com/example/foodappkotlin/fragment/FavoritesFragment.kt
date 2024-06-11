package com.example.foodappkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappkotlin.videoModel.HomeViewModel
import com.example.foodappkotlin.activites.MainActivity
import com.example.foodappkotlin.adapter.FavoritesMealsAdapter
import com.example.foodappkotlin.databinding.FragmentFavoritesBinding
import com.example.foodappkotlin.dialog.MealBottomSheetFragment
import com.google.android.material.snackbar.Snackbar

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: HomeViewModel
    private val favoritesAdapter by lazy { FavoritesMealsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(LayoutInflater.from(requireContext()))
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
        observeFavorites()

        favoritesAdapter.onLongItemClick = { meal ->
            val mealBottomSheetFragment = MealBottomSheetFragment.newInstance(meal.idMeal)
            mealBottomSheetFragment.show(childFragmentManager, "Meal Info")
        }
    }

    private fun initListener() {
        itemTouchHelper()

    }


    private fun itemTouchHelper() {
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.UP or ItemTouchHelper.DOWN
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteMeal(favoritesAdapter.differ.currentList[position])


                Snackbar.make(requireView(), "meal deleted", Snackbar.LENGTH_LONG).setAction(
                    "Undo",
                    View.OnClickListener {
                        viewModel.insertMeal(favoritesAdapter.differ.currentList[position])
                    }
                ).show()
            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavorites)
    }


    private fun prepareRecyclerView() {
        binding.rvFavorites.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = favoritesAdapter
        }
    }

    private fun observeFavorites() {
        viewModel.observerFavoritesMealsLiveData().observe(requireActivity(), Observer { meals ->
            favoritesAdapter.differ.submitList(meals)
        })
    }

}