package com.thangdn6.carsimulator.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thangdn6.carsimulator.database.dao.TireDao
import com.thangdn6.carsimulator.model.Tire

@Database(entities = [Tire::class], version = 1)
abstract class TireDatabase : RoomDatabase(){
    abstract fun getDao(): TireDao
}