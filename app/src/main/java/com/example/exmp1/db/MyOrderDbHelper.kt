package com.example.exmp1.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyOrderDbHelper(context: Context) : SQLiteOpenHelper(context, MyDbOrderClass.DATABASE_NAME, null, MyDbOrderClass.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) { db?.execSQL(MyDbOrderClass.CREATE_TABLE) }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        db?.execSQL(MyDbOrderClass.SQL_DELETE_TABLE)
        onCreate(db)
    }
}