package com.example.mvvmprojecttemplate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(
    var id: Int,
    @Expose
    @SerializedName("tensp")
    var name: String,
    @Expose
    @SerializedName("mota")
    var desc: String,
    @Expose
    @SerializedName("gia")
    var price: Double,
    @Expose
    @SerializedName("hinhanh")
    var picture: String,
    @Expose
    @SerializedName("idLoai")
    var typeId: Int
):Serializable{
    fun getImgLink() = "http://dzdemo.xyz/img/$picture.jpg"
    fun bindPrice() = "Price: $price VND"
}
