package com.example.mvvmprojecttemplate.extension

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mvvmprojecttemplate.R


@BindingAdapter("bind_src")
    fun getImageFromUrl(img:ImageView,url:String){
        Glide.with(img).load(url)
            .error(R.drawable.error)
            .into(img)
    }