package com.thangdn6.carsimulator.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.thangdn6.carsimulator.model.Tire

@Dao
interface TireDao {
    @Query("select * from tire")
    suspend fun getListTire() : List<Tire>

    @Insert
    suspend fun insertTire(tire: Tire)

    @Update
    suspend fun updateTire(tire: Tire)

    @Query("select * from tire")
     fun getListTireLiveData() : LiveData<List<Tire>>

}