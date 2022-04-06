package com.example.currency

import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class TTarget : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ttarget)

        val rv = findViewById<RecyclerView>(R.id.rv)
        val btnadd = findViewById<ImageButton>(R.id.imageButton)

        val localeid = Locale("in","ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeid)

        val baranglist = mutableListOf(
            BTarget("Laptop Gaming", (numberFormat.format(30000000)).toDouble())
        )

        //memasukkan data ke dalam adapter dan menampilkan ke dalam rv
        val adapter = BTargetAdapter(baranglist)
        rv.adapter = adapter
        rv.layoutManager= LinearLayoutManager(this)

        btnadd.setOnClickListener{
            val brg =
        }
    }
}