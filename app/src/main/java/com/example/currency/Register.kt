package com.example.currency

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
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

        buttonLogin2.setOnClickListener { openMyActivity2() }
        //buttonRegister2.setOnClickListener { openMyActivity3() }

        buttonRegister2.setOnClickListener {
            val intent = Intent(this@Register, confirm::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

            val builder = NotificationCompat.Builder(this, "channel 1")
                .setSmallIcon(R.drawable.ic_baseline_monetization_on_24)
                .setContentTitle("TabunganKu")
                .setContentText("JANGAN BAGIKAN kode ini kepada siapa pun, TERMASUK TIM TABUNGANKU. WASPADA PENIPUAN! MASUK KE AKUN dengan kode verifikasi 144477.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(0, builder.build())

                createNotificationChannel()
            }

            //openMyActivity3()

        }

    }

    private fun createNotificationChannel() {
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

    //private fun createNotificationChannel() {
      //  TODO("Not yet implemented")
    //}

    private fun openMyActivity2() {
        val intentmasuk2 = Intent(this@Register, Login::class.java)
        startActivity(intentmasuk2)
    }

    //private fun openMyActivity3() {
        //val intentdaftar2 = Intent(this@Register, confirm::class.java)
        //startActivity(intentdaftar2)

    }
