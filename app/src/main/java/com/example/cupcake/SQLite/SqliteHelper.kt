package com.example.cupcake.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.cupcake.Model.User

class SqliteHelper(context : Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        private const val DATABASE_NAME = "cupcake.db"
        private const val DATABASE_VERSION = 1

        // User table and columns
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db : SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_USERS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT,$COLUMN_EMAIL TEXT, $COLUMN_PASSWORD TEXT)"
        db!!.execSQL(createTableQuery)
    }

    override fun onUpgrade(db : SQLiteDatabase? , oldVersion : Int , newVersion : Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_USERS"
        db!!.execSQL(dropTableQuery)
        onCreate(db)
    }
    fun addUser(username: String,email : String, password: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }
        val newRowId = db.insert(TABLE_USERS, null, values)
        db.close()
        return newRowId
    }
    fun getUser(email: String): User? {
        val db = this.readableDatabase
        val selection = "$COLUMN_EMAIL = ?"
        val selectionArgs = arrayOf(email)

        val cursor = db.query(
            TABLE_USERS,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var user: User? = null
        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(COLUMN_ID)
            val passwordIndex = cursor.getColumnIndex(COLUMN_PASSWORD)

            if (idIndex != -1 && passwordIndex != -1) {
                val id = cursor.getLong(idIndex)
                val password = cursor.getString(passwordIndex)
                user = User(id, email, password)
            }
        }
        cursor.close()
        db.close()
        return user
    }

}