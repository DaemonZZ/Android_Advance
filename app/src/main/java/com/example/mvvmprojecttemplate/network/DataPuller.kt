package com.example.mvvmprojecttemplate.network

import com.example.mvvmprojecttemplate.network.service.IDataService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataPuller {
    val BASE_URL ="http://dzdemo.xyz/"
    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val service : IDataService by lazy {
        buildRetrofit().create(IDataService::class.java)
    }
}