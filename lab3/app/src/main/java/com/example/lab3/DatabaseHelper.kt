package com.example.lab3

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "questions.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE questions (id INTEGER PRIMARY KEY AUTOINCREMENT, question TEXT, answer TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS questions")
        onCreate(db)
    }

    fun insertData(question: String, answer: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("question", question)
            put("answer", answer)
        }
        val result = db.insert("questions", null, values)
        db.close()
        return result != -1L
    }

    fun getAllData(): List<Pair<String, String>> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT question, answer FROM questions", null)
        val dataList = mutableListOf<Pair<String, String>>()

        if (cursor.moveToFirst()) {
            do {
                val question = cursor.getString(0)
                val answer = cursor.getString(1)
                dataList.add(Pair(question, answer))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return dataList
    }

    fun clearDatabase() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM questions")
        db.close()
    }
}