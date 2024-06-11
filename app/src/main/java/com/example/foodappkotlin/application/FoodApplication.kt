package com.example.foodappkotlin.application

import android.app.Application
import android.content.Context
import com.example.foodappkotlin.db.MealDatabase
import com.example.foodappkotlin.di.listModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FoodApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin(this)
        MealDatabase.getInstance(this)
    }

    private fun setupKoin(context: Context) {
        startKoin {
            androidContext(context)
            modules(listModule)
        }
    }
}