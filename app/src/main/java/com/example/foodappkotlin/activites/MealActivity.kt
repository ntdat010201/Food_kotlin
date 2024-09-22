package com.example.foodappkotlin.activites

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.foodappkotlin.R
import com.example.foodappkotlin.databinding.ActivityMealBinding
import com.example.foodappkotlin.db.MealDatabase
import com.example.foodappkotlin.extension.showImgGlide
import com.example.foodappkotlin.pojo.Meal
import com.example.foodappkotlin.utils.Const.Companion.MEAL_ID
import com.example.foodappkotlin.utils.Const.Companion.MEAL_NAME
import com.example.foodappkotlin.utils.Const.Companion.MEAL_THUMB
import com.example.foodappkotlin.videoModel.MealViewModel
import com.example.foodappkotlin.videoModel.MealViewModelFactory

class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding

    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var youTubeLink: String
    private lateinit var mealMvvm: MealViewModel
    private var mealToSave: Meal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initData()
        initView()
        initListener()
    }

    private fun initData() {
        val mealDatabase = MealDatabase.getInstance(this)
        val viewModelFactory = MealViewModelFactory(mealDatabase)

        mealMvvm = ViewModelProvider(this, viewModelFactory)[MealViewModel::class.java]
        getDataView()

        mealMvvm.getMealDetail(mealId)

    }


    private fun initView() {
        setDataInView()
        observerMealDetailsLiveData()
        loadingCase()
    }

    private fun initListener() {
        onYoutubeImageClick()
        onFavoriteClick()
        onOrderClick()
        onNumberOfItems()
    }

    private fun onNumberOfItems() {
        var sum = 1
        binding.quantity.text = sum.toString()

        binding.removeCircle.setOnClickListener {
            sum -= 1
            if (sum >= 0) {
                binding.quantity.text = sum.toString()
            } else {
                sum = 0
                binding.quantity.text = sum.toString()
            }
        }

        binding.addCircle.setOnClickListener {
            sum += 1
            binding.quantity.text = sum.toString()
        }
    }

    private fun onOrderClick() {
        binding.order.setOnClickListener {

            if (mealName != null && mealThumb != null) {
                val intent = Intent(this, PlaceYourOrderActivity::class.java)
                intent.apply {
                    putExtra(MEAL_ID, mealId)
                    putExtra(MEAL_NAME, mealName)
                    putExtra(MEAL_THUMB, mealThumb)
                }
                startActivity(intent)
            }
        }
    }

    private fun onFavoriteClick() {
        binding.btnAddFavorite.setOnClickListener {
            mealToSave?.let {
                mealMvvm.insertMeal(it)
                Toast.makeText(this, "Meal saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onYoutubeImageClick() {
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youTubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDetailsLiveData() {
        mealMvvm.observerMealDetailsLiveData().observe(this, object : Observer<Meal> {
            @SuppressLint("SetTextI18n")
            override fun onChanged(meal: Meal?) {
                onResponseCase()
                mealToSave = meal

                binding.tvCategory.text = " Category : ${meal!!.strCategory}"
                binding.tvArea.text = "Area : ${meal.strArea}"
                binding.tvInstructionsSteps.text = meal.strInstructions

                youTubeLink = meal.strYoutube!!

            }
        })
    }

    private fun getDataView() {
        val intent = intent
        mealId = intent.getStringExtra(MEAL_ID)!!
        mealName = intent.getStringExtra(MEAL_NAME)!!
        mealThumb = intent.getStringExtra(MEAL_THUMB)!!
    }

    private fun setDataInView() {
        showImgGlide(this@MealActivity, mealThumb, binding.imgMealDetail)
        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun loadingCase() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnAddFavorite.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }

    private fun onResponseCase() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnAddFavorite.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }

}