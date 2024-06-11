package com.example.foodappkotlin.di

import com.example.foodappkotlin.videoModel.HomeViewModel
import com.example.foodappkotlin.activites.MainActivity
import com.example.foodappkotlin.fragment.UserFragment
import com.example.foodappkotlin.fragment.FavoritesFragment
import com.example.foodappkotlin.fragment.HomeFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainActivity = module {
    scope<MainActivity> {
        fragment { HomeFragment() }
        fragment { FavoritesFragment() }
        fragment { UserFragment() }

        viewModel {HomeViewModel(get())}
    }



}
val listModule = listOf(
    mainActivity
)
