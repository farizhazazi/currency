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