package com.example.mvvmprojecttemplate.network.service

import com.example.mvvmprojecttemplate.model.Item
import com.example.mvvmprojecttemplate.model.ItemType
import retrofit2.Call
import retrofit2.http.GET

interface IDataService {
    @GET("getdata.php")
    fun getItems(): Call<List<Item>>
    @GET("gettype.php")
    fun getTypes():Call<List<ItemType>>
}