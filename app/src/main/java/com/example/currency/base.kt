package com.example.currency

import  android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class base  : Application(){
    companion object{
        const val CHANNEL_1_ID = "channel 1"
    }

    override fun onCreate() {
        super.onCreate()
        createNoficationChannel()
    }

    private fun createNoficationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel1: NotificationChannel = NotificationChannel(
                CHANNEL_1_ID,
                "Channel Satu",
                NotificationManager.IMPORTANCE_HIGH //behaviour
            )
            channel1.description = "Ini adalah notif"

            val manager =  getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel1)

        }
    }
}