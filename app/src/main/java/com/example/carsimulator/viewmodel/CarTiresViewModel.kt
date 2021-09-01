package com.example.carsimulator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carsimulator.model.Tire
import com.example.carsimulator.repository.TireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarTiresViewModel @Inject constructor(private val repo:TireRepository):ViewModel() {
    companion object{
        private const val TAG = "ThangDN6 - CarTiresViewModel"
    }
    @Inject lateinit var scope:CoroutineScope
     val carTires : MutableLiveData<List<Tire>>  by lazy {
         MutableLiveData<List<Tire>>().also {
             scope.launch {
                 it.postValue(repo.getAllTire())
             }
         }
     }

    suspend fun updateTire(tire: Tire){
        repo.updateTire(tire)
    }

}