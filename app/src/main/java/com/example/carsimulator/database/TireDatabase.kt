package com.example.carsimulator.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.carsimulator.database.dao.TireDao
import com.example.carsimulator.model.Tire

@Database(entities = [Tire::class], version = 1)
abstract class TireDatabase : RoomDatabase(){
    abstract fun getDao():TireDao
}