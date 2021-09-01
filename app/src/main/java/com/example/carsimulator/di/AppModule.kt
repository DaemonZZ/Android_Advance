package com.example.carsimulator.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.carsimulator.database.TireDatabase
import com.example.carsimulator.database.dao.TireDao
import com.example.carsimulator.model.Tire
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideIOScope() = CoroutineScope(Dispatchers.IO)
    @Singleton
    @Provides
    fun provideDAO(db:TireDatabase) = db.getDao()

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        scope : CoroutineScope,
        daoProvider:Provider<TireDao>
    ):TireDatabase{
        return Room.databaseBuilder(context,TireDatabase::class.java,"tire_db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    scope.launch {
                        fakeTransData(daoProvider.get())
                    }
                }
            })
            .fallbackToDestructiveMigration()
            .build()
    }

    private suspend fun fakeTransData(dao:TireDao){
        for(i in 1..4){
            dao.insertTire(
                Tire().apply {
                    id = i
                    temperature = 50f
                    pressure = 2f
                }
            )
        }
    }
}