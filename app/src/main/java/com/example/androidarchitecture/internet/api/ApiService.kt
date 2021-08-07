package com.example.androidarchitecture.internet.api

import com.example.androidarchitecture.model.Country
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    companion object{
        val gson = GsonBuilder().setLenient().create()
        val apiService = Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/keeguon/2310008/raw/bdc2ce1c1e3f28f9cab5b4393c7549f38361be4e/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }


    @GET("countries.json")
    fun getCountries(): Call<List<Country>>
}