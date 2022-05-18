package com.example.currency

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View

class SharePrefHelper (context: Context, name: String){
    val username = "Nama"
    val useremail = "Email"
    val userphone = "Phone"
    val password = "Password"

    private  var myPreferences : SharedPreferences

    init {
        myPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    }

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor)-> Unit){
        val editMe = edit()
        operation(editMe)
        editMe.apply()

    }

    var nama : String?
        get() = myPreferences.getString(username,"Empty")
        set(value) {
            myPreferences.editMe {
                it.putString(username, value)
            }
        }

    var Email: String?
        get() = myPreferences.getString(useremail, "Empty")
        set(value){
            myPreferences.editMe {
                it.putString(useremail, value)
            }
        }

    var phone: String?
        get() = myPreferences.getString(userphone, "Empty")
        set(value){
            myPreferences.editMe {
                it.putString(userphone, value)
            }
        }

    var pass: String?
        get() = myPreferences.getString(password, "Empty")
        set(value){
            myPreferences.editMe {
                it.putString(password, value)
            }
        }



    fun clearValues(){
        myPreferences.edit().clear().commit()
    }

}