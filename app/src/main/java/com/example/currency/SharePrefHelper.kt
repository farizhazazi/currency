package com.example.currency

import android.content.Context
import android.content.SharedPreferences

class SharePrefHelper (context: Context, name: String){
    val username = "Nama"
    val useremail = "Email"
    val userphone = "Phone"
    //val password = "Password"

    private  var myPreferences : SharedPreferences

    init {
        myPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    }

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor)-> Unit){
        val editMe = edit()
        operation(editMe)
        editMe.commit()

    }
/*
    var nama : String
       // get() = myPreferences.getString(username, "User Name")
        set(value){
            myPreferences.editMe {
                it.putString(username, value)
            }
        }

    var Email: String
        //get() = myPreferences.getString(useremail, "Email")
        set(value){
            myPreferences.editMe {
                it.putString(useremail, value)
            }
        }

    var phone: String
        //get() = myPreferences.getString(userphone, "Phone Number")
        set(value){
            myPreferences.editMe {
                it.putString(userphone, value)
            }
        }
*/
    fun clearValues(){
        myPreferences.edit().clear().commit()
    }

}