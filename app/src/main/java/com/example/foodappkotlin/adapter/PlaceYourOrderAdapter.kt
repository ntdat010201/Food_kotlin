package com.example.foodappkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappkotlin.databinding.OrderItemBinding
import com.example.foodappkotlin.extension.showImgGlide
import com.example.foodappkotlin.pojo.MealsByCategory

class PlaceYourOrderAdapter(
    val context: Context,
    var mealId: String,
    var mealName: String,
    var mealThumb: String
) : RecyclerView.Adapter<PlaceYourOrderAdapter.OrderViewHolder>() {
//    private lateinit var context : Context


    class OrderViewHolder(val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            OrderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        showImgGlide(context, mealThumb, holder.binding.imgOrder)

        holder.binding.nameOrder.text = mealName
    }

}