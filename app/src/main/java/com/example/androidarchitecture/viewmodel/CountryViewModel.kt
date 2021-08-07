package com.example.androidarchitecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitecture.internet.DataPuller
import com.example.androidarchitecture.model.Country

class CountryViewModel : ViewModel() {
    private val countries: MutableLiveData<List<Country>> by lazy {
        MutableLiveData<List<Country>>().also {
            loadCountries(it)
        }
    }

    fun getCountry(): LiveData<List<Country>> {
        return countries
    }

    private fun loadCountries(mutableLiveData: MutableLiveData<List<Country>>) {
        mutableLiveData.postValue(
            //Fetch data using https request
//            DataPuller.getCountries(BASE_URL)


            //Fetch data using Retrofit
            DataPuller.getCountriesUsingRetrofit()
        )


    }

}