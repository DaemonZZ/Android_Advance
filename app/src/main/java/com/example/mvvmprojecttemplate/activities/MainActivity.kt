package com.example.mvvmprojecttemplate.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmprojecttemplate.R
import com.example.mvvmprojecttemplate.adapter.ViewPagerAdapter
import com.example.mvvmprojecttemplate.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val tabTitle = listOf("Shop","Inventory","Profile")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vpMain.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabMain,binding.vpMain){
            tab,index -> tab.text =  tabTitle[index]
        }.attach()

        binding.vpMain.isUserInputEnabled = false
    }
}