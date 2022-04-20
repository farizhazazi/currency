package com.example.currency

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.fragment.FragmentNavigator
//class myAReceiver : BroadcastReceiver(){
//    override fun onReceive(context: Context, intent: Intent) {
//
//        val i = Intent(context, AddTarget::class.java)
//        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        val pendingIntent = PendingIntent.getActivity(context,0,i,0)
//
//        val builder = NotificationCompat.Builder(context!!, "android")
//            .setSmallIcon(R.drawable.ic_notif)
//            .setContentTitle("TabunganKu")
//            .setContentText("Target tabungan anda tersisa 7 hari lagi. Ayo segera capai targetmu!!")
//            .setAutoCancel(true)
//            .setDefaults(NotificationCompat.DEFAULT_ALL)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setContentIntent(pendingIntent)
//
//        val notificationManager = NotificationManagerCompat.from(context)
//        notificationManager.notify(123,builder.build())
//    }
//}