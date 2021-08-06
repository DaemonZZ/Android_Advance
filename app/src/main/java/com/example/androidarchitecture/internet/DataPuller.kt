package com.example.androidarchitecture.internet

import com.example.androidarchitecture.model.Country
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object DataPuller {
    fun getCountries(url:String):List<Country>{
        val link = URL(url)
        val http = link.openConnection() as HttpsURLConnection
        val br = BufferedReader(InputStreamReader(http.inputStream))
        val data = StringBuilder("")
        var line = br.readLine()
        while(line!=null){
            data.append(line)
            line=br.readLine()
        }
        val rawArray = JSONArray(data.toString())
        val countries = mutableListOf<Country>()
        for(i in 1 until rawArray.length()){
            val element = rawArray.getJSONObject(i)
            val country = Country(element.getString("code"),element.getString("name"))
            countries.add(country)
        }
        return countries

    }
}