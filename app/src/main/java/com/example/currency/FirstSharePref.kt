package com.example.currency

import android.content.Context
import android.content.SharedPreferences

class FirstSharePref(context: Context) {
    private val keyPref = "FIRST_RUN"
    private var myPref : SharedPreferences
    init{
        myPref = context.getSharedPreferences("sharePrefKey", Context.MODE_PRIVATE)
    }

    var firstRun : Boolean
    get() = myPref.getBoolean(keyPref, true)
    set(value) {
        myPref.edit().putBoolean(keyPref,value).commit()
    }
}