package com.example.foodappkotlin.activites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.foodappkotlin.R
import com.example.foodappkotlin.adapter.MyViewPagerAdapter
import com.example.foodappkotlin.base.BaseActivity
import com.example.foodappkotlin.databinding.ActivityMainBinding
import com.example.foodappkotlin.db.MealDatabase
import com.example.foodappkotlin.fragment.FavoritesFragment
import com.example.foodappkotlin.fragment.HomeFragment
import com.example.foodappkotlin.fragment.UserFragment
import com.example.foodappkotlin.videoModel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val homeFragment by inject<HomeFragment>()
    private val favoritesFragment by inject<FavoritesFragment>()
    private val userFragment by inject<UserFragment>()

    val viewModel by viewModel<HomeViewModel> { parametersOf(MealDatabase.getInstance(this)) }

    private var myViewPagerAdapter: MyViewPagerAdapter? = null

    var nameEmail: String? = null

//    val viewModel: HomeViewModel by lazy {
//        val mealDatabase = MealDatabase.getInstance(this)
//        val homeViewModelProviderFactory = HomeViewModelFactory(mealDatabase)
//        ViewModelProvider(this, homeViewModelProviderFactory)[HomeViewModel::class.java]
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initData()

    }

    private fun initData() {
        nameEmail = intent.getStringExtra("EMAIL_USER")
        Log.d("DAT", "initData: $nameEmail")

        myViewPagerAdapter = MyViewPagerAdapter(this)
        myViewPagerAdapter!!.setFragments(homeFragment, favoritesFragment, userFragment)

        /* khởi tạo và set viewpager2 tablayout*/
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.adapter = myViewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_home)
                1 -> tab.setIcon(R.drawable.ic_favorite)
                else -> tab.setIcon(R.drawable.ic_category)
            }
        }.attach()
    }


}