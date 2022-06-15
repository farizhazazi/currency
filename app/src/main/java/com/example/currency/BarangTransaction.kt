package com.example.currency

import MyDataBase.DBTarget
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException

class BarangTransaction (context: Context) {
    private val myDBHelper = DBHelper(context)
    private val dbwirte = myDBHelper.writableDatabase
    fun viewAllName():List<String>{
        val nameList: ArrayList<String> = ArrayList<String>()
        val selectQuery = "SELECT ${DBTarget.targetTable.kolomnama}" +
                " FROM ${DBTarget.targetTable.tabletarget}"

        val db = myDBHelper.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var userName: String
        if (cursor.moveToFirst()) {
            do {
                userName = cursor.getString((cursor.getColumnIndex(DBTarget.targetTable.kolomnama))).toString()
                nameList.add(userName)
            } while (cursor.moveToNext())
        }
        return nameList
    }

    fun addBarang(target: Target):Long{
        val db = myDBHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBTarget.targetTable.kolomnama, target.nama)
        contentValues.put(DBTarget.targetTable.kolomharga, target.harga)
        contentValues.put(DBTarget.targetTable.kolomjangka, target.jangka)
        val success = db.insert(DBTarget.targetTable.tabletarget,
            null, contentValues)
        db.close()
        return success
    }
}