package com.example.customdrawablenavigation.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.customdrawablenavigation.fragment.Fragment1
import com.example.customdrawablenavigation.fragment.Fragment2
import com.example.customdrawablenavigation.fragment.Fragment3

class PagerAdapter(activity:AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
       return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> Fragment1()
            1 -> Fragment2()
            2 -> Fragment3()
            else -> throw NoSuchElementException()
        }
    }
}