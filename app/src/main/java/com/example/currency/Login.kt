package com.example.currency

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class Login : AppCompatActivity() {

    private lateinit var buttonLogin: Button
    private lateinit var Regis: Button
    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin = findViewById(R.id.button)
       //buttonLogin.setOnClickListener { open1() }

        Regis = findViewById(R.id.button2)
        //Regis.setOnClickListener { open2() }


        //notificaton
        notificationManager = NotificationManagerCompat.from(this)
        Regis.setOnClickListener{
            val intent = Intent(this, Login::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

            val builder = NotificationCompat.Builder(this, "channel 1")
                .setSmallIcon(R.drawable.ic_baseline_add_24)
                .setContentTitle("title")
                .setContentText("notif content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(0, builder.build())

                createNotificationChannel()
            }

        }
         fun createNotificationChannel() {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = getString(R.string.channel_name)
                val descriptionText = getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel("channel 1", name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }

        //menjalankan service
        val servicelogin = Intent(this, LoginSuccesService::class.java)
        buttonLogin.setOnClickListener{
            startService(servicelogin)
            open1()

        }

        Regis.setOnClickListener{open2()}
    }

    private fun createNotificationChannel() {
        TODO("Not yet implemented")
    }

    private fun open1() {
        val intentMasuk = Intent(this@Login, MainActivity::class.java)
        startActivity(intentMasuk)
    }

    private fun open2() {
        val intentRegis = Intent(this@Login, Register::class.java)
        startActivity(intentRegis)
    }



}