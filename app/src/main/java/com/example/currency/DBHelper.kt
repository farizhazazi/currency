package com.example.currency

import MyDataBase.DBTarget
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

//membuat database
class DBHelper(context: Context): SQLiteOpenHelper (context, DATABASE_NAME, null, DATABASE_VERSIONS) {
    override fun onCreate(data: SQLiteDatabase?) {
        val create_tableTarget = ("CREATE TABLE" +
                DBTarget.targetTable.tabletarget + "(" +
                DBTarget.targetTable.kolomid + " INTEGER PRIMARY KEY," +
                DBTarget.targetTable.kolomnama + " TEXT," +
                DBTarget.targetTable.kolomharga + " TEXT," +
                DBTarget.targetTable.kolomjangka+ " TEXT" + ")")
        data?.execSQL(create_tableTarget)
    }

    override fun onUpgrade(data: SQLiteDatabase, versilama: Int, versibaru: Int) {
        data.execSQL("DROP TABLE IF EXISTS" + DBTarget.targetTable.tabletarget)
        onCreate(data)
    }


    companion object{
        private val DATABASE_VERSIONS = 1
        private val DATABASE_NAME = "MySQLMobile.db"
    }

    //tambah database (manipulasi database/ menulis)
    fun addTarget(target: TargetP) : Long{
        val data = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBTarget.targetTable.kolomnama, target.nama)
        contentValues.put(DBTarget.targetTable.kolomharga, target.harga)
        contentValues.put(DBTarget.targetTable.kolomjangka, target.jangka)

        val sukses = data.insert(DBTarget.targetTable.tabletarget, null, contentValues)
        data.close()

        return sukses
    }

    //view sql
    fun view() : List<String>{
        val listnama = ArrayList<String>()
        val pilih = "SELECT ${DBTarget.targetTable.kolomnama} " +"FROM ${DBTarget.targetTable.tabletarget}"

        val data = this.readableDatabase
        var kursor: Cursor? = null
        try{
            kursor = data.rawQuery(pilih, null)
        }
        catch (e: SQLException){
            return ArrayList()
        }

        var username: String = ""
        if(kursor.moveToFirst()){
            do{
                username = kursor.getString((kursor.getColumnIndex(DBTarget.targetTable.kolomnama))).toString()
                listnama.add(username)
            }while (kursor.moveToNext())
        }
        return listnama

    }
}