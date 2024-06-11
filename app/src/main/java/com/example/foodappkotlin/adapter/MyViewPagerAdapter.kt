package com.example.foodappkotlin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodappkotlin.fragment.UserFragment
import com.example.foodappkotlin.fragment.FavoritesFragment
import com.example.foodappkotlin.fragment.HomeFragment

class MyViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private lateinit var homeFragment: HomeFragment
    private lateinit var favoritesFragment: FavoritesFragment
    private lateinit var userFragment: UserFragment

    fun setFragments(
        homeFragment: HomeFragment,
        favoritesFragment: FavoritesFragment,
        userFragment: UserFragment
    ) {
        this.homeFragment = homeFragment
        this.favoritesFragment = favoritesFragment
        this.userFragment = userFragment
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> FavoritesFragment()
            2 -> UserFragment()
            else -> HomeFragment()
        }
    }


}