package com.example.mvvmprojecttemplate.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import com.example.mvvmprojecttemplate.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}