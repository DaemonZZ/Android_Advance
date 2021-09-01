package com.example.carsimulator.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.carsimulator.model.Tire

@Dao
interface TireDao {
    @Query("select * from tire where id <5")
    suspend fun getListTire() : List<Tire>

    @Insert
    suspend fun insertTire(tire:Tire)

    @Update
    suspend fun updateTire(tire: Tire)

    @Query("select * from tire where id <5")
     fun getListTireLiveData() : LiveData<List<Tire>>

}