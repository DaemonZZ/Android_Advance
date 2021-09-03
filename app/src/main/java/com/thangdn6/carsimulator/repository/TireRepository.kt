package com.thangdn6.carsimulator.repository

import androidx.lifecycle.LiveData
import com.thangdn6.carsimulator.database.dao.TireDao
import com.thangdn6.carsimulator.model.Tire
import javax.inject.Inject

class TireRepository @Inject constructor(private val dao: TireDao) {
     fun getAllTireLiveData(): LiveData<List<Tire>> {
        return  dao.getListTireLiveData()
    }

    suspend fun insertTire(tire: Tire){
        dao.insertTire(tire)
    }

    suspend fun updateTire(tire: Tire){
        dao.updateTire(tire)
    }

    suspend fun getAllTire():List<Tire>{
        return dao.getListTire()
    }

}