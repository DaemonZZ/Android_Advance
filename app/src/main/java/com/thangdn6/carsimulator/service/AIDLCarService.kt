package com.thangdn6.carsimulator.service

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.thangdn6.carsimulator.IServiceBinder
import com.thangdn6.carsimulator.ITireInfoChangeListener
import com.example.carsimulator.R
import com.thangdn6.carsimulator.database.dao.TireDao
import com.thangdn6.carsimulator.model.Tire
import com.thangdn6.carsimulator.utils.ActionFlags.ACTION_START_SERVICE
import com.thangdn6.carsimulator.utils.ActionFlags.ACTION_STOP_SERVICE
import com.thangdn6.carsimulator.utils.ActionFlags.CHANNEL_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AIDLCarService : Service() {

    companion object{
        private const val TAG = "ThangDN6 - AIDLCarService"
    }
    @Inject
     lateinit var dao: TireDao

    @Inject
    lateinit var scope: CoroutineScope

    private lateinit var listener: ITireInfoChangeListener

    private lateinit var listTires:List<Tire>

    private val binder = object : IServiceBinder.Stub(){
        override fun getListTires(): List<Tire> {
            return  this@AIDLCarService.listTires
        }

        override fun saveInfor(tire: Tire?) {
            if(tire !=null){
                updateTire(tire)
            }
        }

        override fun getTireById(id: Int): Tire {
            val tire = listTires.first { it.id == id }
            Log.i(TAG, "getTireById: $id  ,  ${tire.temperature}")
            return tire
        }

        override fun setTireInforChangeListener(listener: ITireInfoChangeListener) {
            this@AIDLCarService.listener = listener
        }

    }

    private fun updateTire(tire: Tire){
        scope.launch {
            dao.updateTire(tire)
            listTires = dao.getListTire()
            listener.onChange()
            listener.notify("Tires's infor changed")
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag", "LaunchActivityFromNotification")
    fun startService(){
        val intent = Intent(this, AIDLCarService::class.java).apply {
            action = ACTION_START_SERVICE
        }

        val pendingIntent = PendingIntent.getService(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT)

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.car)
            .setContentTitle("AIDL Server")
            .setContentText("Tap to stop")
            .setContentIntent(pendingIntent)
            .build()

        startForeground(14,notification)
    }

    override fun onCreate() {
        super.onCreate()
        startService()
    }
    override fun onBind(intent: Intent): IBinder {
        scope.launch {
            Log.d(TAG, "onBind: ")
            listTires = dao.getListTire()
        }
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        scope.launch {
            Log.d(TAG, "restart: ")
            listTires = dao.getListTire()
            if(::listener.isInitialized){
                listener.onChange()//
            }
        }
        if (ACTION_STOP_SERVICE == intent?.action){
            stopForeground(true)
            stopSelf()
        }
        return START_STICKY
    }

}