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
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

//sharepref
private val PrefFileName = "MyFilePref"

class Register : AppCompatActivity(), View.OnClickListener{
    private lateinit var buttonLogin2: Button
    private lateinit var buttonRegister2 : Button

    //notif
    private lateinit var notificationManager: NotificationManagerCompat

    //share
    private lateinit var buttonshare : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonLogin2 = findViewById(R.id.button3)
        buttonRegister2 = findViewById(R.id.button4)

        buttonLogin2.setOnClickListener { openMyActivity2() }
        //buttonRegister2.setOnClickListener { openMyActivity3() }

       // buttonRegister2.setOnClickListener(this)
       buttonRegister2.setOnClickListener {
            val intent = Intent(this@Register, Register::class.java).apply {
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
            buttonRegister2.setOnClickListener(this)
            //openMyActivity3()

        }

        buttonshare = findViewById(R.id.button6)
        buttonshare.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        var share = SharePrefHelper(this, PrefFileName)

        // share
        val usernametext = findViewById<EditText>(R.id.editTextTextPersonName4)
        val emaill = findViewById<EditText>(R.id.editTextPhone)
        val nohp = findViewById<EditText>(R.id.editTextTextPassword4)
        val pw = findViewById<EditText>(R.id.editTextTextPassword2)


        when(p0?.id){
            R.id.button4 ->{
                share.nama = usernametext.text.toString()
                share.Email = emaill.text.toString()
                share.phone = nohp.text.toString()
                share.pass = pw.text.toString()

                Toast.makeText(this, "Register Done", Toast.LENGTH_SHORT).show()

                usernametext.text.clear()
                emaill.text.clear()
                nohp.text.clear()
                pw.text.clear()

                openMyActivity3()
            }

            R.id.button6 ->{
                usernametext.setText(share.nama)
                emaill.setText(share.Email)
                nohp.setText(share.phone)
                pw.setText(share.pass)
            }
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

    private fun openMyActivity3() {
        val intentdaftar2 = Intent(this@Register, confirm::class.java)
        startActivity(intentdaftar2)

    }


}