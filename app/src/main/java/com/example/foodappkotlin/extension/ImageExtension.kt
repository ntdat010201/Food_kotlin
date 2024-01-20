package com.example.foodappkotlin.extension

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun showImgGlide(context: Context, path: String, view: ImageView){
    Glide.with(context).load(path).into(view)
}