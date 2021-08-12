package com.example.mvvmprojecttemplate.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvvmprojecttemplate.databinding.FragmentProfileBinding
import com.example.mvvmprojecttemplate.extension.getImageFromUrl
import android.content.Intent
import android.net.Uri


class ProfileFragment:Fragment() {
    private lateinit var binding:FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        getImageFromUrl(binding.avatar,"http://dzdemo.xyz/img/a.jpg")
        getImageFromUrl(binding.icoFacebook,"http://dzdemo.xyz/img/facebook.png")
        getImageFromUrl(binding.icoInsta,"http://dzdemo.xyz/img/insta.png")
        getImageFromUrl(binding.icoBlog,"http://dzdemo.xyz/img/blog.png")
        getImageFromUrl(binding.icoExpress,"http://dzdemo.xyz/img/wordpress.png")
        binding.fbLink.setOnClickListener { openLink(binding.fbLink.text.toString()) }
        binding.blogLink.setOnClickListener { openLink(binding.blogLink.text.toString()) }
        binding.instaLink.setOnClickListener { openLink(binding.instaLink.text.toString()) }
        binding.webLink.setOnClickListener { openLink(binding.webLink.text.toString()) }
        return binding.root
    }

    private fun openLink(url:String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}