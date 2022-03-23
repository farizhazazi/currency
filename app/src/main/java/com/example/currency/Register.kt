package com.example.currency

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Register : AppCompatActivity() {
    private lateinit var buttonLogin2: Button
    private lateinit var buttonRegister2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonLogin2 = findViewById(R.id.button3)
        buttonRegister2 = findViewById(R.id.button4)

        buttonLogin2.setOnClickListener{openMyActivity2()}

        buttonRegister2.setOnClickListener{openMyActivity3()}
    }

    private fun openMyActivity2() {
        val intentmasuk2 = Intent(this@Register, Login::class.java)
        startActivity(intentmasuk2)
    }

    private fun openMyActivity3() {
        val intentdaftar2 = Intent(this@Register, MainActivity::class.java)
        startActivity(intentdaftar2)
    }
}