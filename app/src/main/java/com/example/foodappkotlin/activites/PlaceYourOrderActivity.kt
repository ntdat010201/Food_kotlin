package com.example.foodappkotlin.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodappkotlin.R
import com.example.foodappkotlin.adapter.PlaceYourOrderAdapter
import com.example.foodappkotlin.databinding.ActivityPlaceYourOrderBinding
import com.example.foodappkotlin.utils.Const

class PlaceYourOrderActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlaceYourOrderBinding
    var isDeliveryOn: Boolean = false

    private lateinit var  placeYourOrderAdapter : PlaceYourOrderAdapter

    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceYourOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initView()
        initListener()
    }

    private fun initData() {
        getDataView()
    }

    private fun initView() {
        // recyclerView
        layoutRecycler()
    }

    private fun layoutRecycler() {
        binding.cartItemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PlaceYourOrderActivity)
            placeYourOrderAdapter = PlaceYourOrderAdapter(this@PlaceYourOrderActivity,mealId,mealName, mealThumb)
            adapter = placeYourOrderAdapter
        }
    }

    private fun initListener() {
        clickCheckSwitchDelivery()
    }

    private fun clickCheckSwitchDelivery() {
        binding.switchDelivery.setOnCheckedChangeListener { compoundButton, isChecked ->
            //set switchDelivery
            if(isChecked) {
                binding.inputAddress.visibility = View.VISIBLE
                binding.inputCity.visibility = View.VISIBLE
                binding.inputState.visibility = View.VISIBLE
                binding.inputZip.visibility = View.VISIBLE
                binding.tvDeliveryCharge.visibility = View.VISIBLE
                binding.tvDeliveryChargeAmount.visibility = View.VISIBLE
                isDeliveryOn = true

            } else {
                binding.inputAddress.visibility = View.GONE
                binding.inputCity.visibility = View.GONE
                binding.inputState.visibility = View.GONE
                binding.inputZip.visibility = View.GONE
                binding.tvDeliveryCharge.visibility = View.GONE
                binding.tvDeliveryChargeAmount.visibility = View.GONE
                isDeliveryOn = false

            }
        }
    }

    private fun getDataView() {
        // getData từ MealActivity
        val intent = intent
        mealId = intent.getStringExtra(Const.MEAL_ID)!!
        mealName = intent.getStringExtra(Const.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(Const.MEAL_THUMB)!!
    }


}