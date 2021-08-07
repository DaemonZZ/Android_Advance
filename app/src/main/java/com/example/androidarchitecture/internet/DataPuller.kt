package com.example.androidarchitecture.internet

import android.util.Log
import android.widget.Toast
import com.example.androidarchitecture.internet.api.ApiService
import com.example.androidarchitecture.model.Country
import kotlinx.coroutines.CoroutineScope
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.math.log

object DataPuller {
    private const val TAG = "DataPuller"
    /**
     * @Fetch Countries using Https request
     * @param url: link
     */
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

    /**
     * @Fetch Countries using Retrofit
     */
     fun getCountriesUsingRetrofit():List<Country>{
         val response = ApiService.apiService.getCountries().execute()
         val list = response.body() as List<Country>

         Log.d(TAG, "onResponse: ${list.size}")
        return list
    }
}