package com.example.currency

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class MyAirplaneReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //menampilkan pesan ketika mode pesawat
        if(Settings.System.getInt(context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0)==0){
            Toast.makeText(context, "Online",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Offline",Toast.LENGTH_SHORT).show()
        }
    }
}