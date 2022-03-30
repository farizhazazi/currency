package com.example.currency

import android.app.NotificationManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Register : AppCompatActivity() {
    private lateinit var buttonLogin2: Button
    private lateinit var buttonRegister2 : Button

    //notif
    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonLogin2 = findViewById(R.id.button3)
        buttonRegister2 = findViewById(R.id.button4)

        buttonLogin2.setOnClickListener{openMyActivity2()}
        buttonRegister2.setOnClickListener{openMyActivity3()}

        buttonRegister2.setOnClickListener{
            val title = getTitle().toString()
            //val message =
                val builder = NotificationCompat.Builder(this, base.CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_baseline_monetization_on_24)
        }

        //notif
        notificationManager = NotificationManagerCompat.from(this)

    }

    private fun openMyActivity2() {
        val intentmasuk2 = Intent(this@Register, Login::class.java)
        startActivity(intentmasuk2)
    }

    private fun openMyActivity3() {
        val intentdaftar2 = Intent(this@Register, confirm::class.java)
        startActivity(intentdaftar2)

    }
}