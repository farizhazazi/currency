package com.example.currency

import android.app.NotificationManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationManagerCompat

class confirm : AppCompatActivity() {

    private lateinit var buttonconfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        buttonconfirm = findViewById(R.id.button5)
        buttonconfirm.setOnClickListener{open()}


    }

    private  fun open(){
        val c = Intent(this, MainActivity::class.java)
        startActivity(c)
    }
}