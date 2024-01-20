package com.example.foodappkotlin.di

import com.example.foodappkotlin.activites.MainActivity
import com.example.foodappkotlin.fragment.CategoriesFragment
import com.example.foodappkotlin.fragment.FavoritesFragment
import com.example.foodappkotlin.fragment.HomeFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val mainActivity = module {
    scope<MainActivity> {
        fragment { HomeFragment() }
        fragment { FavoritesFragment() }
        fragment { CategoriesFragment() }
    }
}
val listModule = listOf(
    mainActivity
)
