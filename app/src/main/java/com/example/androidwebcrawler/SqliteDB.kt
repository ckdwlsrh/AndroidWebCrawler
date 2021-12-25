package com.example.androidwebcrawler

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteDB(
    context: Context?
) : SQLiteOpenHelper(context, "user_data", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("Create table USER (" +
                "id integer primary key autoincrement," +
                "username text not null," +
                "password text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }
}