package com.example.currency

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.widget.EditText
import android.widget.Toast

var sp : SoundPool? = null
class Login : AppCompatActivity() {

    private lateinit var buttonLogin: Button
    private lateinit var Regis: Button
    private lateinit var UsernameEditText: EditText
    private lateinit var PasswordEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin = findViewById(R.id.button)
       //buttonLogin.setOnClickListener { open1() }

        Regis = findViewById(R.id.button2)
        //Regis.setOnClickListener { open2() }

        UsernameEditText = findViewById(R.id.editTextTextPersonName2)
        PasswordEditText = findViewById(R.id.editTextTextPassword)


        //menjalankan service
        val servicelogin = Intent(this, LoginSuccesService::class.java)
        buttonLogin.setOnClickListener{
            if (UsernameEditText.text.toString() == "test" &&
                PasswordEditText.text.toString() == "password") {
                var a = Toast.makeText(this,"Success",Toast.LENGTH_SHORT)
                startService(servicelogin)
                open1()
            } else {
                var a = Toast.makeText(this,"Failed",Toast.LENGTH_SHORT)
            }


        }
        Regis.setOnClickListener{open2()}

        //soundpool
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
         createNewSoundPool()
        }
        else{
            createOldNewSoundPool()
        }

        sp?.setOnLoadCompleteListener{SoundPool, id, status->
            if(status != 0){
                Toast.makeText(this,"Login Error",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
            }
        }
        var soundID = sp!!.load(this, R.raw.dering,1)


        sp!!.play(soundID, 1.0f, 1.0f,0,0,1.0f)
    }

    //soundpool
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun createNewSoundPool(){
        sp = SoundPool.Builder().setMaxStreams(15).build()

    }

    @Suppress("SOUNDPOOL")
    private fun createOldNewSoundPool(){
        sp = SoundPool(15, AudioManager.STREAM_MUSIC,0)
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