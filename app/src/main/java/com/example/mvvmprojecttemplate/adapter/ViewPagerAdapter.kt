package com.example.mvvmprojecttemplate.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mvvmprojecttemplate.fragment.ShopFragment
import com.example.mvvmprojecttemplate.fragment.ItemFragment
import com.example.mvvmprojecttemplate.fragment.InventoryFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ShopFragment()
            1 -> InventoryFragment()
            2 -> ItemFragment()
            else -> throw NoSuchElementException()
        }
    }
}