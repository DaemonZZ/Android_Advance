package com.example.mvvmprojecttemplate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ItemType(
    var id:Int,
    @Expose
    @SerializedName("tenloai")
    var type:String,
    @Expose
    @SerializedName("hinhanh")
    var picture:String
):Serializable{
    fun getImgLink() = "http://dzdemo.xyz/img/$picture.jpg"
}
