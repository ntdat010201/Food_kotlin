package com.example.foodappkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappkotlin.databinding.CategoryItemBinding
import com.example.foodappkotlin.extension.showImgGlide
import java.util.Locale.Category

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private var categoriesList = ArrayList<com.example.foodappkotlin.pojo.Category>()

    fun setCategoryList(categoriesList:List<com.example.foodappkotlin.pojo.Category>) {
        this.categoriesList = categoriesList as ArrayList<com.example.foodappkotlin.pojo.Category>
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(val binding : CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        showImgGlide(holder.itemView.context,categoriesList[position].strCategoryThumb,holder.binding.imgCategory)
        holder.binding.tvCategoryName.text = categoriesList[position].strCategory
    }

}