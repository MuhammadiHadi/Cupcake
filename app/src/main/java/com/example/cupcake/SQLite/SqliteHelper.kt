package com.example.cupcake.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.cupcake.Model.User
import com.example.cupcake.Model.UserProfile
import com.example.cupcake.Utils.SessionManager
import kotlin.properties.Delegates

class SqliteHelper(context : Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val sessionManager = SessionManager(context)
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val DATABASE_NAME = "cupcake.db"
        private const val DATABASE_VERSION = 1

        // User table and columns
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_PHONE = "phone"
    }

    override fun onCreate(db : SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_USERS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT,$COLUMN_EMAIL TEXT, $COLUMN_PASSWORD TEXT,$COLUMN_PHONE NUMBER)"
        db!!.execSQL(createTableQuery)
    }

    override fun onUpgrade(db : SQLiteDatabase? , oldVersion : Int , newVersion : Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_USERS"
        db!!.execSQL(dropTableQuery)
        onCreate(db)
    }
    fun addUser(username: String,email : String, password: String,phone:String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_PHONE,phone)
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
                val editor = sharedPreferences.edit()
                editor.putLong("userId", id)
                editor.apply()
            }
        }
        cursor.close()
        db.close()
        return user
    }
    fun getUserById(): UserProfile? {
        val loggedInUserId=sharedPreferences.getLong("userId",-1)
        sessionManager.currentUserId = loggedInUserId.toLong()
        val currentUserId = sessionManager.currentUserId  // Assuming you have a session manager instance

        if (currentUserId == -1L) {
            // User is not logged in, return null or handle the case accordingly
            return null
        }

        val db = this.readableDatabase
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(currentUserId.toString())

        val cursor = db.query(
            TABLE_USERS,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var user: UserProfile? = null
        if (cursor.moveToFirst()) {
            val usernameIndex = cursor.getColumnIndex(COLUMN_USERNAME)
            val emailIndex = cursor.getColumnIndex(COLUMN_EMAIL)
            val passwordIndex = cursor.getColumnIndex(COLUMN_PASSWORD)
            val phoneIndex=cursor.getColumnIndex(COLUMN_PHONE)

            if (usernameIndex != -1 && emailIndex != -1 && passwordIndex != -1) {
                val username = cursor.getString(usernameIndex)
                val email = cursor.getString(emailIndex)
                val password = cursor.getString(passwordIndex)
                val phone = cursor.getString(phoneIndex)
                user =UserProfile(username, email, phone)
            }
        }
        cursor.close()
        db.close()
        return user
    }

}