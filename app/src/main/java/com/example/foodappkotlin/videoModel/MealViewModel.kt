package com.example.foodappkotlin.VideoModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappkotlin.db.MealDatabase
import com.example.foodappkotlin.pojo.Meal
import com.example.foodappkotlin.pojo.MealList
import com.example.foodappkotlin.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(
   var  mealDatabase: MealDatabase
) : ViewModel() {
    private var mealDetailsLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id : String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList>{
            override fun onResponse(call: Call<MealList>?, response: Response<MealList>?) {
                if (response!!.body() != null){
                    mealDetailsLiveData.value = response.body().meals[0]
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>?, t: Throwable) {
                Log.d("MealDetails", t.message.toString())
            }
        })
    }

    fun observerMealDetailsLiveData() : LiveData<Meal>{
        return mealDetailsLiveData
    }

    fun insertMeal(meal : Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().upsert(meal)
        }
    }

}