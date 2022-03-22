package com.example.currency

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class LoginSuccesService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
        return START_STICKY
   //return super.onStartCommand(intent, flags, startId)
    }

}